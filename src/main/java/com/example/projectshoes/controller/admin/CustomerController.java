package com.example.projectshoes.controller.admin;

import com.example.projectshoes.constant.SystemConstant;
import com.example.projectshoes.model.CustomerModel;
import com.example.projectshoes.service.ICustomerService;
import com.example.projectshoes.utils.FormUtil;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin-customers")
public class CustomerController extends HttpServlet {

  @Inject
  ICustomerService customerService;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    CustomerModel customerModel = FormUtil.toModel(CustomerModel.class, req);
    if (customerModel.getPage() == null) {
      customerModel.setPage(1);
    }
    List<CustomerModel> customerModels = customerService.findAllCustomer(customerModel.getPage());
    for (CustomerModel c : customerModels) {
      if (c.getUser().getStatus().equals("Active")) {
        c.setBadge("success");
      } else {
        c.setBadge("danger");
      }
    }
    customerModel.setTotalItem(SystemConstant.totalCustomers);
    customerModel.setTotalPage((SystemConstant.totalCustomers / 5) + 1);
    req.setAttribute("model", customerModel);
    req.setAttribute("customers", customerModels);
    RequestDispatcher rd = req.getRequestDispatcher("/views/admin/LIST/ListCustomer.jsp");
    rd.forward(req, resp);
  }
}
