package com.example.projectshoes.service.impl;

import com.example.projectshoes.dao.ICategoryDAO;
import com.example.projectshoes.model.CategoryModel;
import com.example.projectshoes.service.ICategoryService;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;

public class CategoryService implements ICategoryService {

  @Inject
  ICategoryDAO categoryDAO;

  @Override
  public List<CategoryModel> findAll() {
    return categoryDAO.findAll();
  }

  @Override
  public Long saveCategory(CategoryModel categoryModel) {
    categoryModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    categoryModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    Long id = categoryDAO.saveCategory(categoryModel);
    categoryModel.setId(id);
    return id;
  }

}
