package com.example.projectshoes.service.impl;

import com.example.projectshoes.dao.IRoleDAO;
import com.example.projectshoes.dao.IUserDAO;
import com.example.projectshoes.model.CartModel;
import com.example.projectshoes.model.LineItemModel;
import com.example.projectshoes.model.RoleModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.service.IUserService;
import com.example.projectshoes.utils.HashingUtil;
import com.example.projectshoes.utils.SessionUtil;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class UserService implements IUserService {

  @Inject
  IUserDAO userDAO;
  @Inject
  IRoleDAO roleDAO;

  @Override
  public UserModel findByUsernameAndPassword(String username, String password) {
    UserModel userModel = findByUserName(username);
    try {
      if (userModel != null && (password.equals(userModel.getPassword())
          || HashingUtil.verifyAndUpdateHash(password,
          userModel.getPassword()))) {
        return userModel;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  @Override
  public UserModel findByUserName(String username) {
    return userDAO.findByUserName(username);
  }

  @Override
  public UserModel findByEmail(String email) {
    return userDAO.findByEmail(email);
  }

  @Override
  public UserModel findByUserID(Long id) {
    return userDAO.findUserById(id);
  }

  @Override
  public Long save(UserModel userModel) {
    RoleModel roleModel = roleDAO.findRoleById(2L);
    userModel.setRoleId(2L);
    userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    userModel.setCreatedBy(userModel.getUsername());
    userModel.getRoles().add(roleModel);
    userModel.setStatus("Active");
    userModel.setBadge("success");
    userModel.setPassword(HashingUtil.hash(userModel.getPassword()));
    return userDAO.save(userModel);
  }

  @Override
  public void update(UserModel userModel) {
    userModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    userModel.setModifiedBy(userModel.getUsername());
    userDAO.update(userModel);
  }

  @Override
  public List<UserModel> findAll() {
    return userDAO.findAll();
  }

  @Override
  public void removeCart(HttpServletRequest req) {
    CartModel cart = (CartModel) SessionUtil.getInstance().getValue(req, "cart");
    if (cart != null) {
      List<LineItemModel> lineItemModelList = cart.getItems();
      for (LineItemModel item : lineItemModelList) {
        cart.removeItem(item);
        if (lineItemModelList.size() == 0) {
          break;
        }
      }
    }
  }
}
