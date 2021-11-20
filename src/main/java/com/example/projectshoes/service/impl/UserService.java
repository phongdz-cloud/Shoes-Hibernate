package com.example.projectshoes.service.impl;

import com.example.projectshoes.constant.SystemQueries;
import com.example.projectshoes.dao.ICacheDAO;
import com.example.projectshoes.dao.IRoleDAO;
import com.example.projectshoes.dao.IUserDAO;
import com.example.projectshoes.dao.impl.CacheDAO;
import com.example.projectshoes.model.RoleModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.service.IUserService;
import com.example.projectshoes.utils.HashingUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;

public class UserService implements IUserService {

  private final ICacheDAO cacheDAO = new CacheDAO();

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Inject
  IUserDAO userDAO;
  @Inject
  IRoleDAO roleDAO;


  @Override
  public UserModel findByUsernameAndPassword(String username, String password) {
    UserModel userModel = (UserModel) cacheDAO.getObject(
        SystemQueries.FINDUSERBYUSERNAME + username);
    try {
      if (userModel == null) {
        userModel = findByUserName(username);
        if (userModel != null && HashingUtil.verifyAndUpdateHash(password,
            userModel.getPassword())) {
          cacheDAO.setObject(SystemQueries.FINDUSERBYUSERNAME + username, userModel);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return userModel;
  }

  @Override
  public UserModel findByUserName(String username) {
    UserModel user = (UserModel) cacheDAO.getObject(SystemQueries.FINDUSERBYUSERNAME + username);
    if (user == null) {
      user = userDAO.findByUserName(username);
      cacheDAO.setObject(SystemQueries.FINDUSERBYUSERNAME + username, user);
    }
    return user;
  }

  @Override
  public UserModel findByEmail(String email) {
    UserModel user = (UserModel) cacheDAO.getObject(SystemQueries.FINDUSERBYEMAIL + email);
    if (user == null) {
      user = userDAO.findByEmail(email);
      cacheDAO.setObject(SystemQueries.FINDUSERBYEMAIL + email, user);
    }
    return user;
  }

  @Override
  public UserModel findByUserID(Long id) {
    UserModel user = (UserModel) cacheDAO.getObject(SystemQueries.FINDUSERBYID + id);
    if (user == null) {
      user = userDAO.findUserById(id);
      cacheDAO.setObject(SystemQueries.FINDUSERBYID + id, user);
    }
    return user;
  }

  @Override
  public Long save(UserModel userModel) {
    RoleModel roleModel = roleDAO.findRoleById(1L);
    userModel.setRoleId(2L); // USER
    userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    userModel.setCreatedBy(userModel.getUsername());
    userModel.getRoles().add(roleModel);
    userModel.setStatus("Active");
    userModel.setBadge("success");
    userModel.setPassword(HashingUtil.hash(userModel.getPassword()));
    cacheDAO.updateFindALL(SystemQueries.FINDALLUSER, userDAO.findAll());
    cacheDAO.updateFindBy(SystemQueries.FINDUSERBYUSERNAME + userModel.getUsername(), userModel);
    return userDAO.save(userModel);
  }

  @Override
  public void update(UserModel userModel) {
    userModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    userModel.setModifiedBy(userModel.getUsername());
    cacheDAO.updateFindALL(SystemQueries.FINDALLUSER, userDAO.findAll());
    cacheDAO.updateFindBy(SystemQueries.FINDUSERBYUSERNAME + userModel.getUsername(), userModel);
    userDAO.update(userModel);
  }

  @Override
  public List<UserModel> findAll() {
    List<UserModel> results = null;
    try {
      String jsonString = (String) cacheDAO.getObject(SystemQueries.FINDALLUSER);
      if (jsonString == null) {
        System.out.println("Không chạm cache server!");
        results = userDAO.findAll();
        cacheDAO.setObject(SystemQueries.FINDALLUSER, objectMapper.writeValueAsBytes(results));
      } else {
        results = objectMapper.readValue(jsonString, new TypeReference<List<UserModel>>() {
        });
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return results;
  }
}
