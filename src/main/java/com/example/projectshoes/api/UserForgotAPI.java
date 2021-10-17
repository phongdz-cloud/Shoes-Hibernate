package com.example.projectshoes.api;

import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.service.IUserService;
import com.example.projectshoes.utils.FieldUtil;
import com.example.projectshoes.utils.HttpUtil;
import com.example.projectshoes.utils.JavaMailUtil;
import com.example.projectshoes.utils.PathUtil;
import java.io.IOException;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

@WebServlet(urlPatterns = {"/api-user-forgot/*"})
public class UserForgotAPI extends HttpServlet {

  @Inject
  private IUserService userService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String userEmail = PathUtil.pathInf(req)[1];
    Boolean flag = true;
    ObjectMapper mapper = new ObjectMapper();
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    UserModel userModel = userService.findByEmail(userEmail);
    if (userModel == null) {
      flag = false;
    } else {
      try {
        FieldUtil.isCheckNewPassword = true;
        JavaMailUtil.sendMail(userEmail);
      } catch (MessagingException e) {
        e.printStackTrace();
      }
    }
    mapper.writeValue(resp.getOutputStream(), flag);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String userEmail = PathUtil.pathInf(req)[1];
    ObjectMapper mapper = new ObjectMapper();
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    UserModel oddUserModel = HttpUtil.of(req.getReader()).toModel(UserModel.class);
    UserModel newUserModel = userService.findByEmail(userEmail);
    if (newUserModel != null) {
      newUserModel.setPassword(oddUserModel.getPassword());
      userService.update(newUserModel);
      FieldUtil.isCheckNewPassword = false;
      mapper.writeValue(resp.getOutputStream(), true);
    } else {
      mapper.writeValue(resp.getOutputStream(), false);
    }
  }
}
