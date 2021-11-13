package com.example.projectshoes;

import com.example.projectshoes.dao.IProductDAO;
import com.example.projectshoes.dao.IRoleDAO;
import com.example.projectshoes.dao.IUserDAO;
import com.example.projectshoes.dao.impl.ProductDAO;
import com.example.projectshoes.dao.impl.RoleDAO;
import com.example.projectshoes.dao.impl.UserDAO;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.model.RoleModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.paging.PageRequest;
import com.example.projectshoes.paging.Pageble;
import com.example.projectshoes.service.IProductService;
import com.example.projectshoes.service.impl.ProductService;

import javax.inject.Inject;

public class TestHibernate {

  static IRoleDAO roleService = new RoleDAO();
  static IUserDAO userService = new UserDAO();

  @Inject
  IProductService productService;
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
      ProductModel productModel=new ProductModel();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
