package com.example.projectshoes.dao.impl;

import com.example.projectshoes.dao.IProductDAO;
import com.example.projectshoes.mapper.ProductMapper;
import com.example.projectshoes.model.CategoryModel;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.paging.Pageble;
import com.example.projectshoes.service.ICategoryService;
import com.example.projectshoes.utils.HibernateUtil;
import org.hibernate.query.Query;
import org.hibernate.Session;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {

  @Inject
  ICategoryService categoryService;
  public ProductDAO() {
    setType(ProductModel.class);
  }

  @Override
  public ProductModel findOne(Long id) {
    StringBuilder sql = new StringBuilder("FROM Product p Where id=:id");
    ProductModel productModel=new ProductModel();
    productModel.setId(id);
    List<ProductModel> productModels = queryHibernate(sql.toString(),productModel);
    return productModels.isEmpty() ? null : productModels.get(0);
  }

  @Override
  public Long saveProduct(ProductModel productModel) {
    CategoryModel categoryModel=categoryService.findByCategoryID(productModel.getCategoryId());
    productModel.setCategory(categoryModel);
    return save(productModel);
  }

  @Override
  public List<ProductModel> findAll(Pageble pageble) {
    Session session = HibernateUtil.getSessionFactory().openSession();
//    ProductModel productModel=new ProductModel();
//    StringBuilder sql = new StringBuilder("from Product p");
//    if (pageble.getOffset() != null && pageble.getLimit() != null) {
//      sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
//    }
    Query q = session.createQuery("FROM Product p");
    q.setFirstResult(pageble.getOffset());
    q.setMaxResults(pageble.getLimit());
    ProductModel productModel=new ProductModel();
    productModel.setListResult(q.getResultList());
    return productModel.getListResult();
  }

  @Override
  public List<ProductModel> findbyCategoryID(Long id) {
    return null;
  }

  @Override
  public void deleteProduct(long id) {
    ProductModel productModel = findById(id);
    delete(productModel);
  }

  @Override
  public void update(ProductModel productModel) {
    saveProduct(productModel);
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
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createSQLQuery("select count(*) from Product p");
    List<BigInteger> count1 =query.list();
    int count=count1.get(0).intValue();
    return count;
  }
}
