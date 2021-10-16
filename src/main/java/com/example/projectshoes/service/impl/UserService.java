package com.example.projectshoes.service.impl;

import com.example.projectshoes.dao.IUserDAO;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.service.IUserService;
import java.sql.Timestamp;
import javax.inject.Inject;

public class UserService implements IUserService {

  @Inject
  IUserDAO userDAO;

  @Override
  public UserModel findByUsernameAndPassword(String username, String password) {
    return userDAO.findByUsernameAndPassword(username, password);
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
  public Long save(UserModel userModel) {
    userModel.setRoleId(2L);
    userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    userModel.setCreatedBy(userModel.getUsername());
    return userDAO.save(userModel);
  }

  @Override
  public void update(UserModel userModel) {
    userDAO.update(userModel);
  }
}
