package com.example.projectshoes.service.impl;

import com.example.projectshoes.constant.SystemQueries;
import com.example.projectshoes.dao.ICacheDAO;
import com.example.projectshoes.dao.ICategoryDAO;
import com.example.projectshoes.model.CategoryModel;
import com.example.projectshoes.service.ICategoryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;

public class CategoryService implements ICategoryService {

  private final ObjectMapper objectMapper = new ObjectMapper();
  @Inject
  ICategoryDAO categoryDAO;
  @Inject
  ICacheDAO cacheDAO;

  @Override
  public List<CategoryModel> findAll() {
    List<CategoryModel> results = null;
    try {
      String jsonString = (String) cacheDAO.getObject(SystemQueries.FINDALLCATEGORY);
      if (jsonString == null) {
        results = categoryDAO.findAll();
        cacheDAO.setObject(SystemQueries.FINDALLCATEGORY, objectMapper.writeValueAsString(results));
      } else {
        results = objectMapper.readValue(jsonString, new TypeReference<CategoryModel>() {
        });
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return results;
  }

  @Override
  public Long saveCategory(CategoryModel categoryModel) {
    categoryModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    categoryModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    Long id = categoryDAO.saveCategory(categoryModel);
    categoryModel.setId(id);
    cacheDAO.updateFindALL(SystemQueries.FINDALLCATEGORY, categoryDAO.findAll());
    return id;
  }

  @Override
  public CategoryModel findByCategoryName(String code) {
    CategoryModel category = (CategoryModel) cacheDAO.getObject(
        SystemQueries.FINDCATEGORYBYNAME + code);
    if(category == null){
      category = categoryDAO.findByCategoryName(code);
      cacheDAO.setObject(SystemQueries.FINDCATEGORYBYNAME + code,category);
    }
    return category;
  }

  @Override
  public CategoryModel findByCategoryID(Long id) {
    CategoryModel category = (CategoryModel) cacheDAO.getObject(SystemQueries.FINDCATEGORYBYID + id);
    if(category == null){
      category = categoryDAO.findByCategoryID(id);
      cacheDAO.setObject(SystemQueries.FINDCATEGORYBYID + id,category);
    }
    return category;
  }
}
