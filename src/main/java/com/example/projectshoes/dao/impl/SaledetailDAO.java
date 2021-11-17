package com.example.projectshoes.dao.impl;

import com.example.projectshoes.dao.ISaledetailDAO;
import com.example.projectshoes.mapper.SaledetailMapper;
import com.example.projectshoes.model.CategoryModel;
import com.example.projectshoes.model.DeliveryModel;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.service.ICategoryService;
import com.example.projectshoes.service.IDeliveryService;
import com.example.projectshoes.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

public class SaledetailDAO extends AbstractDAO<SaledetailModel> implements ISaledetailDAO {
  public SaledetailDAO() {
    setType(SaledetailModel.class);
  }

  @Override
  public SaledetailModel findOne(Long id) {
    StringBuilder sql = new StringBuilder("FROM Saledetail s Where s.id=:id");
    SaledetailModel saledetailModel=new SaledetailModel();
    saledetailModel.setId(id);
    List<SaledetailModel> saledetailModels = queryHibernate(sql.toString(),saledetailModel);
    return saledetailModels.isEmpty() ? null : saledetailModels.get(0);
  }

  @Override
  public Long saveSaledetail(SaledetailModel saledetailModel) {
    return save(saledetailModel);
  }


  @Override
  public List<SaledetailModel> findAll() {
    StringBuilder sql = new StringBuilder("FROM Saledetail s");
    SaledetailModel saledetailModel=new SaledetailModel();
    return queryHibernate(sql.toString(), saledetailModel);

  }

  @Override
  public List<SaledetailModel> findbyUserID(Long id) {
    return null;
  }

  @Override
  public List<SaledetailModel> findbyProductID(Long id) {
    return null;
  }

  @Override
  public List<SaledetailModel> findbyDeliveryID(Long id) {
    return null;
  }


  @Override
  public void deleteSaledetail(long id) {
    SaledetailModel saledetailModel = findById(id);
    delete(saledetailModel);
  }

  @Override
  public void update(SaledetailModel saledetailModel) {
    update(saledetailModel);
  }

  @Override
  public List<SaledetailModel> PageSaledetail(int page) {
    if (page < 1) {
      page = 1;
    }
    int offset = (page - 1) * 5;
    StringBuilder sql = new StringBuilder("select * from Saledetail LIMIT 5 OFFSET ?");
    return query(sql.toString(), new SaledetailMapper(), offset);
  }

  @Override
  public int getTotalItem() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createSQLQuery("select count(*) from Saledetail s");
    List<BigInteger> count1 =query.list();
    int count=count1.get(0).intValue();
    return count;
  }
}
