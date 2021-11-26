package com.example.projectshoes.service.impl;

import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.service.IPaymentServices;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServices implements IPaymentServices {

  private static final String CLIENT_ID = System.getenv("SANDBOX_CLIENT_ID");
  private static final String CLIENT_SECRET = System.getenv("SANDBOX_SECRET");
  private static final String MODE = "sandbox";

  @Override
  public String authorizePayment(SaledetailModel saledetailModel) {
    try {
      Payer payer = getPayerInformation();
      RedirectUrls redirectUrls = getRedirectUrls();
      List<Transaction> listTransactions = getTransactionInformation(saledetailModel);
      Payment requestPayment = new Payment();
      requestPayment.setTransactions(listTransactions)
          .setRedirectUrls(redirectUrls)
          .setPayer(payer)
          .setIntent("authorize");
      APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
      Payment approvedPayment = requestPayment.create(apiContext);
      System.out.println(approvedPayment);
      return getApprovalLink(approvedPayment);
    } catch (PayPalRESTException e) {
      e.printStackTrace();
      return null;
    }
  }

  public Payment getPaymentDetails(String paymentId) {
    APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
    try {
      return Payment.get(apiContext, paymentId);
    } catch (PayPalRESTException e) {
      e.printStackTrace();
    }
    return null;
  }

  private String getApprovalLink(Payment approvedPayment) {
    List<Links> links = approvedPayment.getLinks();
    String approvalLink = null;
    for (Links e : links) {
      if (e.getRel().equalsIgnoreCase("approval_url")) {
        approvalLink = e.getHref();
      }
    }
    return approvalLink;
  }

  private RedirectUrls getRedirectUrls() {
    RedirectUrls redirectUrls = new RedirectUrls();
    redirectUrls.setCancelUrl("http://localhost/PayPalTest/cancel.html");
    redirectUrls.setReturnUrl("http://localhost/PayPalTest/review_payment");
    return redirectUrls;
  }

  private List<Transaction> getTransactionInformation(SaledetailModel saledetailModel) {

    Details details = new Details();
    details.setShipping(String.format("%.2f", 10F));
    details.setSubtotal(String.format("%.2f", 100F));
    details.setTax(String.format("%.2f", 10F));

    Amount amount = new Amount();
    amount.setCurrency("USD");
    amount.setTotal(String.format("%.2f", 120F));
    amount.setDetails(details);

    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    transaction.setDescription(saledetailModel.getProduct().getName());
    ItemList itemList = new ItemList();
    List<Item> items = new ArrayList<Item>();
    Item item = new Item();
    item.setCurrency("USD")
        .setName(saledetailModel.getProduct().getName())
        .setPrice(String.format("%.2f", 100F))
        .setTax(String.format("%.2f", 10F))
        .setQuantity("1");
    items.add(item);
    itemList.setItems(items);
    transaction.setItemList(itemList);
    List<Transaction> listTransaction = new ArrayList<Transaction>();
    listTransaction.add(transaction);
    return listTransaction;
  }

  public Payer getPayerInformation() {
    Payer payer = new Payer();
    payer.setPaymentMethod("paypal");

    PayerInfo payerInfo = new PayerInfo();
    payerInfo.setFirstName("Ho")
        .setLastName("Phong")
        .setEmail("19110262@student.hcmute.edu.vn");
    payer.setPayerInfo(payerInfo);
    return payer;
  }
}
