package com.example.projectshoes.service.impl;

import com.example.projectshoes.dao.IProductDAO;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.paging.Pageble;
import com.example.projectshoes.service.ICategoryService;
import com.example.projectshoes.service.IProductService;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class ProductService implements IProductService {

    @Inject
    IProductDAO productDAO;
    @Inject
    ICategoryService categoryService;
    @Override
    public List<ProductModel> findAll(Pageble pageble,String key) {
        return productDAO.findAll(pageble,key);
    }

    @Override
    public Long saveProduct(ProductModel productModel) {
        productModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        productModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return productDAO.saveProduct(productModel);
    }

    @Override
    public void deleteProduct(long[] ids) {
        for(long id:ids){
            productDAO.deleteProduct(id);
        }
    }
    @Override
    public ProductModel findOne(Long id) {
        return productDAO.findOne(id);
    }
    @Override
    public void update(ProductModel productModel) {
        productModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        productDAO.update(productModel);
    }

    @Override
    public List<ProductModel> pageProduct(int page) {
        return productDAO.PageProduct(page);
    }

    @Override
    public int getTotalItem() {
        return productDAO.getTotalItem();
    }

    @Override
    public List<ProductModel> findbyCategory(Pageble pageble,String code) {
        return productDAO.findbyCategory(pageble,code);
    }

    @Override
    public List<ProductModel> Sort(String sql) {
        String query="";
        if(sql.equals("A-Z")){
            query="FROM Product p ORDER BY p.name ASC";
        }
        else if(sql.equals("price")){
            query="FROM Product p ORDER BY p.price";
        }
        else {

        }
        return productDAO.Sort(query);
    }

    @Override
    public int getTotalItemByCategory(String code) {
        return productDAO.getTotalItemByCategory(code);
    }
}