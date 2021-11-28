package com.example.projectshoes.controller.web;

import com.example.projectshoes.service.IProductService;
import com.example.projectshoes.service.IUserService;
import com.example.projectshoes.service.impl.PaymentServices;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/excute_payment")
public class ExecutePaymentController extends HttpServlet {

  @Inject
  PaymentServices paymentServices;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String paymentId = request.getParameter("paymentId");
    String payerId = request.getParameter("PayerID");
    String url = "/views/web/paymentError.jsp";
    try {

      Payment payment = paymentServices.executePayment(paymentId, payerId);
      PayerInfo payerInfo = payment.getPayer().getPayerInfo();
      Transaction transaction = payment.getTransactions().get(0);

      request.setAttribute("payer", payerInfo);
      request.setAttribute("transaction", transaction);
      url = "/views/web/receipt.jsp";

    } catch (PayPalRESTException ex) {
      request.setAttribute("errorMessage", ex.getMessage());
      ex.printStackTrace();
    }
    RequestDispatcher rq = request.getRequestDispatcher(url);
    rq.forward(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }
}
