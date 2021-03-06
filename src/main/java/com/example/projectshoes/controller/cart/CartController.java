package com.example.projectshoes.controller.cart;

import com.example.projectshoes.model.CartModel;
import com.example.projectshoes.model.LineItemModel;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.service.IPaymentServices;
import com.example.projectshoes.service.IProductService;
import com.example.projectshoes.service.IUserService;
import com.example.projectshoes.utils.SessionUtil;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cart")
public class CartController extends HttpServlet {

  @Inject
  IProductService productService;
  @Inject
  IUserService userService;
  @Inject
  IPaymentServices paymentServices;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
    String url;
    if (userModel == null) {
      url = "/views/web/login.jsp";
    } else {
      Long id = Long.parseLong(req.getParameter("productId"));
      String action = req.getParameter("action");
      ProductModel productModel = new ProductModel();
      productModel = productService.findOne(id);
      String quantityString = req.getParameter("quantity");
      double total;
      CartModel cart = (CartModel) SessionUtil.getInstance().getValue(req, "cart");
      LineItemModel lineItemModel = new LineItemModel();
      int quantity;
      try {
        quantity = Integer.parseInt(quantityString);
        if (quantity < 0) {
          quantity = 1;
        }
      } catch (NumberFormatException nfe) {
        quantity = 1;
      }
      if (cart == null) {
        cart = new CartModel();
        lineItemModel.setProduct(productModel);
        lineItemModel.setQuantity(quantity);
        cart.addItem(lineItemModel);
      } else {
        List<LineItemModel> lineItemModelList = cart.getItems();
        boolean check = false;
        for (LineItemModel item : lineItemModelList) {
          if (item.getProduct().getId().equals(productModel.getId()) && quantity != 0
              && action != null) {
            item.setQuantity(Math.min(productModel.getQuantity(), item.getQuantity() + quantity));
            check = true;
            break;
          }
        }
        if (!check) {
          lineItemModel.setQuantity(quantity);
          lineItemModel.setProduct(productModel);
          if (quantity > 0) {
            cart.addItem(lineItemModel);
          } else {
            cart.removeItem(lineItemModel);
          }
        }
      }
      total = cart.totalPrice(lineItemModel);
      SessionUtil.getInstance().putValue(req, "cart", cart);
      SessionUtil.getInstance().putValue(req, "total", total);
      url = "/views/web/Cart.jsp";
    }
    RequestDispatcher rd = req.getRequestDispatcher(url);
    rd.forward(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");
    String url = "/views/web/Cart.jsp";
    RequestDispatcher rd;
    if (action != null) {
      UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
      if (userModel == null) {
        url = "/views/web/login.jsp";
      } else {
        SaledetailModel saledetailModel = productService.UpdateAfertCheckout(req, userModel);
        if (saledetailModel != null) {
          userService.removeCart(req);
          try {
            String approLink = paymentServices.authorizePayment(saledetailModel);
            resp.sendRedirect(approLink);
          } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("errorMessage", ex.getMessage());
            url = "/views/web/paymentError.jsp";
            rd = req.getRequestDispatcher(url);
            rd.forward(req, resp);
          }
        } else {
          url = "/views/web/Cart.jsp";
          rd = req.getRequestDispatcher(url);
          rd.forward(req, resp);
        }
      }
    }

  }

}
