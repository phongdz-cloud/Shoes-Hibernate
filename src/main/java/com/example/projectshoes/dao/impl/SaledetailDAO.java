package com.example.projectshoes.dao.impl;

import com.example.projectshoes.constant.SystemConstant;
import com.example.projectshoes.dao.ISaledetailDAO;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.utils.HibernateUtil;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class SaledetailDAO extends AbstractDAO<SaledetailModel> implements ISaledetailDAO {

  public SaledetailDAO() {
    setType(SaledetailModel.class);
  }

  @Override
  public SaledetailModel findOne(Long id) {
    SaledetailModel saledetailModel = new SaledetailModel();
    saledetailModel.setId(id);
    List<SaledetailModel> saledetailModels = queryHibernate("FROM Saledetail s Where s.id=:id", saledetailModel);
    return saledetailModels.isEmpty() ? null : saledetailModels.get(0);
  }

  @Override
  public Long saveSaledetail(SaledetailModel saledetailModel) {
    return save(saledetailModel);
  }

  @Override
  public List<SaledetailModel> findAll() {
    return queryHibernate("FROM Saledetail", null);
  }


  @Override
  public List findAll(int pageIndex) {
    List saledetailModels;
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery("FROM Saledetail s ORDER BY s.product.name DESC");
    SystemConstant.totalSaledetail = findAll().size();
    query.setFirstResult((5 * (pageIndex - 1) + 1));
    query.setMaxResults(5);
    saledetailModels = query.list();
    return saledetailModels;
  }

  @Override
  public List<SaledetailModel> findAllbyUserID(Long id, int pageIndex) {
    List saledetailModels;
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery(
        "From Saledetail s Where s.user.id=" + id + " ORDER BY s.product.name DESC");
    SystemConstant.totalPageReceipt = findAllByUserIdTotal(id).size();
    query.setFirstResult((5 * (pageIndex - 1)));
    query.setMaxResults(5);
    saledetailModels = query.list();
    return saledetailModels;
  }

  @Override
  public List<SaledetailModel> findAllByUserIdTotal(Long id) {
    List saledetailModels;
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery("From Saledetail s Where s.user.id=" + id);
    saledetailModels = query.list();
    return saledetailModels;
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
  public int getTotalItem() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createSQLQuery("select count(*) from Saledetail s");
    List<BigInteger> count1 = query.list();
    return count1.get(0).intValue();
  }

  @Override
  public SaledetailModel findbyCode(String code) {
    SaledetailModel saledetailModel = new SaledetailModel();
    saledetailModel.setCode(code);
    List<SaledetailModel> saledetailModels = queryHibernate("FROM Saledetail s Where s.code=:code", saledetailModel);
    return saledetailModels.isEmpty() ? null : saledetailModels.get(0);
  }

  @Override
  public List<SaledetailModel> gettop3() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    SaledetailModel saledetailModel = new SaledetailModel();
    Query q = session.createQuery(
        "select u.product From Saledetail u group by u.product order by sum(u.quantity) desc");
    q.setFirstResult(0);
    q.setMaxResults(3);
    saledetailModel.setListResult(q.getResultList());
    return saledetailModel.getListResult();
  }

}
