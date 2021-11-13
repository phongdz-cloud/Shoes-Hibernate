package com.example.projectshoes.service;

import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.paging.Pageble;

import java.util.List;

public interface IProductService {
    List<ProductModel> findAll(Pageble pageble,String key);
    Long saveProduct(ProductModel productModel);
    void deleteProduct(long[] ids);
    ProductModel findOne(Long id);
    void update(ProductModel productModel);
    List<ProductModel> pageProduct(int page);
    int getTotalItem();
    List<ProductModel> findbyCategory(Pageble pageble,String code);
    List<ProductModel> Sort(String sql);
    int getTotalItemByCategory(String code);
}
