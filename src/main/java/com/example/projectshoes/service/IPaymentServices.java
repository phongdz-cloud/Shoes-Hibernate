package com.example.projectshoes.service;

import com.example.projectshoes.model.CustomerModel;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.model.UserModel;

public interface IPaymentServices {

  String authorizePayment(SaledetailModel saledetailModel);
}
