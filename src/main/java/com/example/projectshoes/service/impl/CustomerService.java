package com.example.projectshoes.service.impl;

import com.example.projectshoes.constant.SystemQueries;
import com.example.projectshoes.dao.ICacheDAO;
import com.example.projectshoes.dao.impl.CustomerDAO;
import com.example.projectshoes.model.CustomerModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

public class CustomerService implements ICustomerService {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Inject
  private CustomerDAO customerDAO;
  @Inject
  private ICacheDAO cacheDAO;

  @Override
  public CustomerModel findCustomerByUser(UserModel user) {
    CustomerModel customerModel = (CustomerModel) cacheDAO.getObject(
        SystemQueries.FINDCUSTOMERBYUSER + user.getUsername());
    if (customerModel == null) {
      customerModel = customerDAO.findCustomerByUser(user);
      cacheDAO.setObject(SystemQueries.FINDCUSTOMERBYUSER + user.getUsername(), customerModel);
    }
    return customerModel;
  }

  @Override
  public CustomerModel findCustomerById(Long id) {
    CustomerModel customerModel = (CustomerModel) cacheDAO.getObject(
        SystemQueries.FINDCUSTOMERBYID + id);
    if (customerModel == null) {
      customerModel = customerDAO.findCustomerById(id);
      cacheDAO.setObject(SystemQueries.FINDCUSTOMERBYID + id, customerModel);
    }
    return customerDAO.findCustomerById(id);
  }

  @Override
  public List<CustomerModel> findAllCustomer() {
    return customerDAO.findAllCustomer();
  }

  @Override
  public List<CustomerModel> findAllCustomer(int pageIndex) {
    return customerDAO.findAllCustomer(pageIndex);
  }

  @Override
  public Long insert(CustomerModel customerModel) {
    Long id = customerDAO.insert(customerModel);
    customerModel.setId(id);
    cacheDAO.updateFindALL(SystemQueries.FINDALLCUSTOMER, customerDAO.findAllCustomer());
    cacheDAO.updateFindBy(SystemQueries.FINDCUSTOMERBYID + id, customerModel);
    return id;
  }

  @Override
  public void update(CustomerModel customerModel) {
    CustomerModel newCustomer = customerDAO.findCustomerByUser(customerModel.getUser());
    if (newCustomer != null) {
      if (StringUtils.isNoneBlank(customerModel.getFirstName())) {
        newCustomer.setFirstName(customerModel.getFirstName());
      }
      if (StringUtils.isNoneBlank(customerModel.getLastName())) {
        newCustomer.setLastName(customerModel.getLastName());
      }
      if (StringUtils.isNoneBlank(customerModel.getPhone())) {
        newCustomer.setPhone(customerModel.getPhone());
      }
      if (StringUtils.isNoneBlank(customerModel.getAddress())) {
        newCustomer.setAddress(customerModel.getAddress());
      }
      if (StringUtils.isNoneBlank(customerModel.getContent())) {
        newCustomer.setContent(customerModel.getContent());
      }
      if (StringUtils.isNoneBlank(customerModel.getAvatar())) {
        newCustomer.getUser().setAvatar(customerModel.getAvatar());
      }
      newCustomer.getUser().setModifiedDate(new Timestamp(System.currentTimeMillis()));
      newCustomer.getUser().setModifiedBy(customerModel.getUser().getUsername());
      newCustomer.setModifiedBy(newCustomer.getUser().getUsername());
      newCustomer.setModifiedDate(new Timestamp(System.currentTimeMillis()));
      newCustomer.getUser().setStatus(customerModel.getUser().getStatus());
      newCustomer.setGender(customerModel.getGender());
      if (customerModel.getUser().getStatus().equals("Inactive")) {
        newCustomer.getUser().setPassword("");
      }
      customerDAO.update(newCustomer);
    } else {
      customerModel.setCreatedBy(customerModel.getUser().getUsername());
      customerModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
      customerDAO.insert(customerModel);
    }
    cacheDAO.updateFindALL(SystemQueries.FINDALLCUSTOMER, customerDAO.findAllCustomer());
    cacheDAO.updateFindBy(SystemQueries.FINDCUSTOMERBYUSER + newCustomer.getUser().getUsername(),
        newCustomer);
  }

  @Override
  public void remove(CustomerModel customerModel) {
    customerDAO.delete(customerModel);
    cacheDAO.deleteCache(SystemQueries.FINDCUSTOMERBYID + customerModel.getId());
    cacheDAO.deleteCache(SystemQueries.FINDCUSTOMERBYUSER + customerModel.getUser().getUsername());
    cacheDAO.updateFindALL(SystemQueries.FINDALLUSER, customerDAO.findAllCustomer());
  }
}
