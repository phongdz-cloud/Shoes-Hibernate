package com.example.projectshoes.dao.impl;

import com.example.projectshoes.dao.ICustomerDAO;
import com.example.projectshoes.mapper.CustomerMapper;
import com.example.projectshoes.model.CustomerModel;
import java.util.List;

public class CustomerDAO extends AbstractDAO<CustomerModel> implements ICustomerDAO {

  @Override
  public CustomerModel findByUserId(Long userId) {
    StringBuilder sql = new StringBuilder("SELECT * FROM customer WHERE user_id=?");
    List<CustomerModel> customers = query(sql.toString(), new CustomerMapper(), userId);
    return customers.isEmpty() ? null : customers.get(0);
  }

  @Override
  public Long save(CustomerModel customerModel) {
    return save(customerModel);
  }

  @Override
  public void update(CustomerModel customerModel) {
    update(customerModel);
  }
}
