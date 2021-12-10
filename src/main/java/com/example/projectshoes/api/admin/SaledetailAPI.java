package com.example.projectshoes.api.admin;

import com.example.projectshoes.constant.SystemConstant;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.pojo.PagePojo;
import com.example.projectshoes.service.IDeliveryService;
import com.example.projectshoes.service.ISaledetailService;
import com.example.projectshoes.utils.FormUtil;
import com.example.projectshoes.utils.HttpUtil;
import com.example.projectshoes.utils.JavaMailUtil;
import com.example.projectshoes.utils.MailTemplateUtil;
import com.example.projectshoes.utils.SessionUtil;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet(urlPatterns = "/api-saledetail/*")
public class SaledetailAPI extends HttpServlet {

  @Inject
  ISaledetailService saledetailService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    SaledetailModel saledetailModel = FormUtil.toModel(SaledetailModel.class, req);
    UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
    List<SaledetailModel> saledetailModelList;
    if (user != null) {
      if (saledetailModel.getPage() == null) {
        saledetailModel.setPage(1);
      }
      saledetailModelList = saledetailService.findAll(saledetailModel.getPage());
      user.setTotalItem(SystemConstant.totalSaledetail);
      user.setTotalPage((SystemConstant.totalSaledetail / 5) + 1);
      user.setPage(saledetailModel.getPage());
      StringBuilder pageSaledetail = new StringBuilder(
          "<table class=\"table table-hover table-borderless align-middle mb-0\"\n"
              + "                       id=\"saledetailsDatatable\">\n"
              + "                    <thead>\n"
              + "                    <tr>\n"
              + "                        <th>ID</th>\n"
              + "                        <th>User_ID</th>\n"
              + "                        <th>Product_ID</th>\n"
              + "                        <th>Delivery_ID</th>\n"
              + "                        <th>Quantity</th>\n"
              + "                        <th>Total</th>\n"
              + "                        <th>Status_delivery</th>\n"
              + "                        <th>Createddate</th>\n"
              + "                        <th>CreatedBy</th>\n"
              + "                    </tr>\n"
              + "                    </thead>\n"
              + "                    <tbody>");
      for (SaledetailModel e : saledetailModelList) {
        String s = "<tr class=\"align-middle\">\n"
            + "                            <td>\n"
            + "                                <input class=\"form-check-input\" type=\"checkbox\"\n"
            + "                                       id=\"checkbox_" + e.getId() + "\" value=\""
            + e.getId() + "\">\n"
            + "                                <label class=\"form-check-label\"\n"
            + "                                       for=\"check" + e.getId() + "\">" + e.getId()
            + "</label>\n"
            + "                            </td>\n"
            + "                            <td>" + e.getUser().getId() + "</td>\n"
            + "                            <td>" + e.getProduct().getName() + "</td>\n"
            + "                            <td>" + e.getDelivery().getId() + "</td>\n"
            + "                            <td>" + e.getQuantity() + "</td>\n"
            + "                            <td>" + e.getTotal() + "</td>\n"
            + "                            <td>\n"
            + "                                    " + e.getDelivery().getStatus() + "\n"
            + "                            </td>\n"
            + "                            <td class=\"text-muted\">" + e.getCreatedDate()
            + "</td>\n"
            + "                            <td class=\"text-muted\">" + e.getCreatedBy() + "</td>\n"
            + "                        </tr>\n";
        pageSaledetail.append(s);
      }
      pageSaledetail.append(" </tbody>\n"
          + "                </table>");
      PagePojo pagePojo = new PagePojo(pageSaledetail.toString(), user.getPage(),
          user.getTotalPage());
      mapper.writeValue(resp.getOutputStream(), pagePojo);

    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    SaledetailModel saledetailModel = HttpUtil.of(req.getReader()).toModel(SaledetailModel.class);
    UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
    saledetailModel.setCreatedBy(userModel.getUsername());
    saledetailModel.setModifiedBy(userModel.getUsername());
    try {
      JavaMailUtil.sendMail(userModel.getEmail(), MailTemplateUtil.templateMailCongratulation(),
          "Conragulation");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    saledetailService.saveSaledetail(saledetailModel);
    mapper.writeValue(resp.getOutputStream(), saledetailModel);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    SaledetailModel saledetailModel = HttpUtil.of(req.getReader()).toModel(SaledetailModel.class);
    saledetailService.deleteSaledetail(saledetailModel.getIds());
    mapper.writeValue(resp.getOutputStream(), "{}");
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    SaledetailModel saledetailModel = HttpUtil.of(req.getReader()).toModel(SaledetailModel.class);
    UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
    saledetailModel.setModifiedBy(userModel.getUsername());
    saledetailService.update(saledetailModel);
    mapper.writeValue(resp.getOutputStream(), saledetailModel);
  }
}
