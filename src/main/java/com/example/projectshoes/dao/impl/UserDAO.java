package com.example.projectshoes.dao.impl;

import com.example.projectshoes.dao.IUserDAO;
import com.example.projectshoes.mapper.UserMapper;
import com.example.projectshoes.model.UserModel;
import java.util.List;
import javax.transaction.Transactional;

@Transactional
public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {


  @Override
  public UserModel findById(Long id) {
    StringBuilder sql = new StringBuilder("SELECT * FROM user WHERE id = ?");
    List<UserModel> userModels = query(sql.toString(), new UserMapper(), id);
    return userModels.isEmpty() ? null : userModels.get(0);
  }

  @Override
  public UserModel findByUsernameAndPassword(String username, String password) {
    StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
    sql.append(" INNER JOIN role AS r ON r.id = u.role_id");
    sql.append(" WHERE username = ? AND password = ?");
    List<UserModel> userModels = query(sql.toString(), new UserMapper(), username, password);
    return userModels.isEmpty() ? null : userModels.get(0);
  }

  @Override
  public UserModel findByUserName(String username) {
    StringBuilder sql = new StringBuilder("SELECT * FROM user AS u ");
    sql.append("WHERE u.username = ?");
    List<UserModel> userModels = query(sql.toString(), new UserMapper(), username);
    return userModels.isEmpty() ? null : userModels.get(0);
  }


  @Override
  public UserModel findByEmail(String email) {
    StringBuilder sql = new StringBuilder("SELECT * FROM user AS u ");
    sql.append("WHERE u.email = ?");
    List<UserModel> userModels = query(sql.toString(), new UserMapper(), email);
    return userModels.isEmpty() ? null : userModels.get(0);
  }

  @Override
  public UserModel findByUserID(Long id) {
    StringBuilder sql = new StringBuilder("SELECT * FROM user Where id=?");
    sql.append(" VALUES(?)");
    List<UserModel> user = query(sql.toString(), new UserMapper(), id);
    return user.isEmpty() ? null : user.get(0);
  }

  @Override
  public Long save(UserModel userModel) {
    return insert(userModel);
  }

  @Override
  public void update(UserModel userModel) {
    StringBuilder sql = new StringBuilder("UPDATE user SET password = ? WHERE id = ?");
    update(sql.toString(), userModel.getPassword(), userModel.getId());
  }


  @Override
  public List<UserModel> findAll() {
    StringBuilder sql = new StringBuilder("SELECT * FROM User");
    return query(sql.toString(), new UserMapper());
  }

}
