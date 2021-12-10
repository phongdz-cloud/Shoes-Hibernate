package com.example.projectshoes.dao.impl;

import com.example.projectshoes.constant.SystemConstant;
import com.example.projectshoes.dao.ICustomerDAO;
import com.example.projectshoes.model.CustomerModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CustomerDAO extends AbstractDAO<CustomerModel> implements ICustomerDAO {

  public CustomerDAO() {
    setType(CustomerModel.class);
  }

  @Override
  public List<CustomerModel> findAllCustomer() {

    return queryHibernate("from Customer", null);
  }

  @Override
  public List<CustomerModel> findAllCustomer(int pageIndex) {
    List customerModels;
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery("from Customer c");
    SystemConstant.totalCustomers = findAllCustomer().size();
    query.setFirstResult((5 * (pageIndex - 1)));
    query.setMaxResults(5);
    customerModels = query.list();
    return customerModels;
  }


  @Override
  public CustomerModel findCustomerById(Long id) {
    return findById(id);
  }

  @Override
  public CustomerModel findCustomerByUser(UserModel userModel) {
    CustomerModel customerModel = new CustomerModel();
    customerModel.setUser(userModel);
    List<CustomerModel> customers = queryHibernate("from Customer c where c.user = :user", customerModel);
    return customers.isEmpty() ? null : customers.get(0);
  }

  @Override
  public Long insert(CustomerModel customerModel) {
    return save(customerModel);
  }

  @Override
  public void update(CustomerModel customerModel) {
    save(customerModel);
  }

  @Override
  public void remove(CustomerModel customerModel) {
    delete(customerModel);
  }
}
