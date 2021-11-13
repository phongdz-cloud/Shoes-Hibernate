package com.example.projectshoes.dao;


import com.example.projectshoes.dao.GenericDAO;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.paging.Pageble;

import java.util.List;

public interface IProductDAO extends GenericDAO<ProductModel> {

    ProductModel findOne(Long id);
    Long saveProduct(ProductModel product);
    List<ProductModel> findAll(Pageble pageble,String key);
    List<ProductModel> findbyCategory(Pageble pageble,String code);
    List<ProductModel> Sort(String sql);
    void deleteProduct(long id);
    void update(ProductModel productModel);
    List<ProductModel> PageProduct(int page);
    int getTotalItem();
    int getTotalItemByCategory(String code);

}
