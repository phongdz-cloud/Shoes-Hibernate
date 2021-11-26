package com.example.projectshoes.api.web;

import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.service.impl.PaymentServices;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet(urlPatterns = {"/api-paypal"})
public class AuthorizePaymentAPI extends HttpServlet {

  @Inject
  PaymentServices paymentServices;


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    RequestDispatcher rq = req.getRequestDispatcher("/views/web/checkoutPayPal.jsp");
    rq.forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    ProductModel productModel = new ProductModel();
    productModel.setName("Vans");
    productModel.setPrice(500F);
    SaledetailModel saledetailModel = new SaledetailModel();
    saledetailModel.setQuantity(5);
    saledetailModel.setStatus_delivery("Ok");
    saledetailModel.setTotal(3.8F);
    saledetailModel.setProduct(productModel);

    String approvalLink = paymentServices.authorizePayment(saledetailModel);
    objectMapper.writeValue(resp.getOutputStream(),approvalLink);
  }
}
