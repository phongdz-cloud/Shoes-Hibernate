package com.example.projectshoes.service;


import com.example.projectshoes.model.CustomerModel;
import com.example.projectshoes.model.UserModel;
import java.util.List;

public interface ICustomerService {

  CustomerModel findCustomerByUser(UserModel userModel);

  CustomerModel findCustomerById(Long id);

  List<CustomerModel> findAllCustomer();

  List<CustomerModel> findAllCustomer(int pageIndex);


  Long insert(CustomerModel customerModel);

  void update(CustomerModel customerModel);

  void remove(CustomerModel customerModel);

}
