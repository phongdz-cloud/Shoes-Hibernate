package com.example.projectshoes.controller.admin;

import com.example.projectshoes.constant.SystemConstant;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.service.ISaledetailService;
import com.example.projectshoes.utils.FormUtil;
import com.example.projectshoes.utils.SessionUtil;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin-saledetail"})
public class SaledetailController extends HttpServlet {

  @Inject
  ISaledetailService saledetailService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    SaledetailModel saledetailModel = FormUtil.toModel(SaledetailModel.class, req);
    UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
    String url = "/views/admin/LIST/ListSaledetail.jsp";
    List<SaledetailModel> saledetailModelList;
    if (user != null) {
      if (saledetailModel.getPage() == null) {
        saledetailModel.setPage(1);
      }
      saledetailModelList = saledetailService.findAll(saledetailModel.getPage());
      user.setTotalItem(SystemConstant.totalSaledetail);
      user.setTotalPage((SystemConstant.totalSaledetail / 5) + 1);
      user.setPage(saledetailModel.getPage());
      req.setAttribute("saledetailModel",saledetailModelList);
      req.setAttribute("model",user);
    }
    RequestDispatcher rq = req.getRequestDispatcher(url);
    rq.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
  }
}
