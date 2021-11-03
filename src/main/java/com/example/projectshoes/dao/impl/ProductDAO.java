package com.example.projectshoes.dao.impl;

import com.example.projectshoes.dao.IProductDAO;
import com.example.projectshoes.mapper.ProductMapper;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.paging.Pageble;
import java.util.List;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {

  public ProductDAO() {
    setType(ProductModel.class);
  }

  @Override
  public ProductModel findOne(Long id) {
    StringBuilder sql = new StringBuilder("SELECT * FROM product Where id=?");
    List<ProductModel> productModels = query(sql.toString(), new ProductMapper(), id);
    return productModels.isEmpty() ? null : productModels.get(0);
  }

  @Override
  public Long save(ProductModel productModel) {
    return save(productModel);
  }

  @Override
  public List<ProductModel> findAll(Pageble pageble) {
    StringBuilder sql = new StringBuilder("SELECT * FROM product");
    if (pageble.getOffset() != null && pageble.getLimit() != null) {
      sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
    }
    return query(sql.toString(), new ProductMapper());
  }

  @Override
  public List<ProductModel> findbyCategoryID(Long id) {
    return null;
  }

  @Override
  public void delete(long id) {
    ProductModel productModel = findById(id);
    delete(productModel);
  }

  @Override
  public void update(ProductModel productModel) {
    update(productModel);
  }

  @Override
  public List<ProductModel> PageProduct(int page) {
    if (page < 1) {
      page = 1;
    }
    int offset = (page - 1) * 5;
    StringBuilder sql = new StringBuilder("select * from product LIMIT 5 OFFSET ?");
    return query(sql.toString(), new ProductMapper(), offset);
  }

  @Override
  public int getTotalItem() {
    String sql = "SELECT count(*) FROM product";
    return count(sql);
  }
}
