package com.example.projectshoes.api.web;

import com.example.projectshoes.constant.SystemConstant;
import com.example.projectshoes.model.CustomerModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.pojo.PagePojo;
import com.example.projectshoes.service.ICustomerService;
import com.example.projectshoes.utils.FormUtil;
import com.example.projectshoes.utils.HttpUtil;
import com.example.projectshoes.utils.SessionUtil;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet(urlPatterns = {"/api-customer/*"})
public class CustomerAPI extends HttpServlet {

  @Inject
  private ICustomerService customerService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    CustomerModel customerModel = FormUtil.toModel(CustomerModel.class, req);
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
    StringBuilder pageCustomer = new StringBuilder(" <table class=\"table table-hover mb-0\">\n"
        + "                                    <thead>\n"
        + "                                    <tr>\n"
        + "                                        <th>Customer</th>\n"
        + "                                        <th>Firstname</th>\n"
        + "                                        <th>Lastname</th>\n"
        + "                                        <th>Gender</th>\n"
        + "                                        <th>Address</th>\n"
        + "                                        <th>Email</th>\n"
        + "                                        <th>Phone</th>\n"
        + "                                        <th>Status</th>\n"
        + "                                        <th>Content</th>\n"
        + "                                    </tr>\n"
        + "                                    </thead>\n"
        + "                                    <tbody>");
    for (CustomerModel c : customerModels) {
      String s = "<tr class=\"align-middle text-muted text-sm\">\n"
          + "                                        <td class=\"text-dark text-base\" style=\"min-width: 250px;\">\n"
          + "                                            <input class=\"me-2 mt-3 form-check-input\"\n"
          + "                                                   type=\"checkbox\"\n"
          + "                                                   id=\"check_" + c.getId()
          + "\" value=\"" + c.getId() + "\"><a\n"
          + "                                                class=\"text-reset text-decoration-none\"\n"
          + "                                                href=\"#!\"><img class=\"avatar p-1 me-2\"\n"
          + "                                                               src=\"" + c.getUser()
          .getAvatar() + "\"\n"
          + "                                        ><strong>" + c.getUser().getUsername()
          + "</strong></a>\n"
          + "                                        </td>\n"
          + "                                        <td>" + c.getFirstName() + "</td>\n"
          + "                                        <td>" + c.getLastName() + "</td>\n"
          + "                                        <td>" + c.getGender() + "</td>\n"
          + "                                        <td>" + c.getAddress() + "</td>\n"
          + "                                        <td>" + c.getUser().getEmail() + "</td>\n"
          + "                                        <td>" + c.getPhone() + "</td>\n"
          + "                                        <td class=\"text-base\"><span\n"
          + "                                                class=\"badge badge-" + c.getBadge()
          + "-light\">" + c.getUser().getStatus() + "</span>\n"
          + "                                        </td>\n"
          + "                                        <td>" + c.getContent() + "</td>\n"
          + "                                    </tr>";
      pageCustomer.append(s);
    }
    pageCustomer.append(" </tbody>\n"
        + "                                </table>");
    PagePojo pagePojo = new PagePojo(pageCustomer.toString(), customerModel.getPage(),
        customerModel.getTotalPage());
    objectMapper.writeValue(resp.getOutputStream(), pagePojo);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    CustomerModel customerModel = HttpUtil.of(req.getReader()).toModel(CustomerModel.class);
    mapper.writeValue(resp.getOutputStream(), customerService.insert(customerModel));
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
    ObjectMapper mapper = new ObjectMapper();
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    CustomerModel customerModel = HttpUtil.of(req.getReader()).toModel(CustomerModel.class);
    customerModel.setUser(userModel);
    if (customerModel.getAvatar() != null) {
      customerModel.getUser().setAvatar(customerModel.getAvatar());
    }
    customerService.update(customerModel);
    mapper.writeValue(resp.getOutputStream(), true);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    CustomerModel customerModel = HttpUtil.of(req.getReader()).toModel(CustomerModel.class);
    for (Long id : customerModel.getIds()) {
      CustomerModel customer = customerService.findCustomerById(id);
      customer.getUser().setStatus("Inactive");
      customer.getUser().setBadge("danger");
      customerService.update(customer);
    }
    mapper.writeValue(resp.getOutputStream(), true);
  }
}
