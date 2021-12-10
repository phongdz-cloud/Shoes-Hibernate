package com.example.projectshoes.api.web;

import com.example.projectshoes.constant.SystemConstant;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.pojo.PagePojo;
import com.example.projectshoes.service.ISaledetailService;
import com.example.projectshoes.utils.FormUtil;
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

@WebServlet(urlPatterns = {"/api-receipt/*"})
public class ReceiptAPI extends HttpServlet {

  @Inject
  ISaledetailService saledetailService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    SaledetailModel saledetailModel = FormUtil.toModel(SaledetailModel.class, req);
    UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
    if (user != null) {
      if (saledetailModel.getPage() == null) {
        saledetailModel.setPage(1);
      }
    }
    List<SaledetailModel> saledetailModelList = saledetailService.findAll(
        saledetailModel.getPage());
    user.setTotalItem(SystemConstant.totalSaledetail);
    user.setTotalPage((SystemConstant.totalSaledetail / 5) + 1);
    user.setPage(saledetailModel.getPage());
    StringBuilder pageReceipt = new StringBuilder(
        " <table class=\"table\">\n"
            + "            <thead>\n"
            + "            <tr>\n"
            + "                <th scope=\"col\">code</th>\n"
            + "                <th scope=\"col\">name</th>\n"
            + "                <th scope=\"col\">size</th>\n"
            + "                <th scope=\"col\">price</th>\n"
            + "                <th scope=\"col\">quantity</th>\n"
            + "                <th scope=\"col\">total</th>\n"
            + "            </tr>\n"
            + "            </thead>\n"
            + "            <tbody id=\"myTable\">");
    for (SaledetailModel e : saledetailModelList) {
      String s = "<tr>\n"
          + "                <td>" + e.getCode() + "</td>\n"
          + "                <td>" + e.getProduct().getName() + "</td>\n"
          + "                <td>" + e.getProduct().getSize() + "</td>\n"
          + "                <td>" + e.getProduct().getPrice() + "$</td>\n"
          + "                <td>" + e.getQuantity() + "</td>\n"
          + "                <td>" + e.getTotal() + "</td>\n"
          + "            </tr>";
      pageReceipt.append(s);
    }
    pageReceipt.append("</tbody>\n"
        + "    </table>");
    PagePojo pagePojo = new PagePojo(pageReceipt.toString(), user.getPage(), user.getTotalPage());
    objectMapper.writeValue(resp.getOutputStream(), pagePojo);
  }
}