package com.example.projectshoes;

import com.example.projectshoes.dao.IProductDAO;
import com.example.projectshoes.dao.IRoleDAO;
import com.example.projectshoes.dao.IUserDAO;
import com.example.projectshoes.dao.impl.ProductDAO;
import com.example.projectshoes.dao.impl.RoleDAO;
import com.example.projectshoes.dao.impl.UserDAO;
import com.example.projectshoes.model.RoleModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.paging.PageRequest;

public class TestHibernate {

  static IRoleDAO roleService = new RoleDAO();
  static IUserDAO userService = new UserDAO();
  static IProductDAO productservice=new ProductDAO();
  public static void main(String[] args) {
    try {
//      RoleModel role = roleService.findRoleById(2L);
//      UserModel userModel = new UserModel();
//      userModel.setUsername("admin");
//      userModel.setPassword("admin");
//      userModel.setEmail("19110262@student.hcmute.edu.vn");
//      userModel.setRoleId(role.getId());
//      userModel.getRoles().add(role);
//      userService.save(userModel);
//        userService.findAll();
//        productservice.findAll()
      productservice.findAll(new PageRequest(1,2));
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
