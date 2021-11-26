package com.example.projectshoes.controller.Cart;

import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.service.IPaymentServices;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/authorize_payment")
public class AuthorizePaymentServlet extends HttpServlet {

  @Inject
  IPaymentServices paymentServices;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ProductModel productModel = new ProductModel();
    productModel.setName("Vans");
    productModel.setPrice(500F);
    SaledetailModel saledetailModel = new SaledetailModel();
    saledetailModel.setQuantity(5);
    saledetailModel.setStatus_delivery("Ok");
    saledetailModel.setTotal(3.8F);
    saledetailModel.setProduct(productModel);
    try {
      String approvalLink = paymentServices.authorizePayment(saledetailModel);
      resp.sendRedirect(approvalLink);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}
