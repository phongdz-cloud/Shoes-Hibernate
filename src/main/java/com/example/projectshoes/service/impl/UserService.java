package com.example.projectshoes.service.impl;

import com.example.projectshoes.dao.IRoleDAO;
import com.example.projectshoes.dao.IUserDAO;
import com.example.projectshoes.model.RoleModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.service.IUserService;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;

public class UserService implements IUserService {

  @Inject
  IUserDAO userDAO;
  @Inject
  IRoleDAO roleDAO;

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
  public UserModel findByUserID(Long id) {
    return userDAO.findById(id);
  }


  @Override
  public Long save(UserModel userModel) {
    RoleModel roleModel = roleDAO.findRoleById(1L);
    userModel.setRoleId(1L);
    userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    userModel.setCreatedBy(userModel.getUsername());
    userModel.getRoles().add(roleModel);
    return userDAO.save(userModel);
  }

  @Override
  public void update(UserModel userModel) {
    userDAO.update(userModel);
  }

  @Override
  public List<UserModel> findAll() {
    return userDAO.findAll();
  }
}
