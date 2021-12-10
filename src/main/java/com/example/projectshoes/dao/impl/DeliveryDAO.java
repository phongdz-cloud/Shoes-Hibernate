package com.example.projectshoes.dao.impl;

import com.example.projectshoes.dao.IDeliveryDAO;
import com.example.projectshoes.model.DeliveryModel;
import com.example.projectshoes.utils.HibernateUtil;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DeliveryDAO extends AbstractDAO<DeliveryModel> implements IDeliveryDAO {

  public DeliveryDAO() {
    setType(DeliveryModel.class);
  }

  @Override
  public List<DeliveryModel> findAll() {
    DeliveryModel deliveryModel = new DeliveryModel();
    return queryHibernate("from Delivery d", deliveryModel);
  }

  @Override
  public DeliveryModel findOne(Long id) {
    DeliveryModel deliveryModel = new DeliveryModel();
    deliveryModel.setId(id);
    List<DeliveryModel> deliveryModels = queryHibernate("FROM Delivery d Where id=:id", deliveryModel);
    return deliveryModels.isEmpty() ? null : deliveryModels.get(0);
  }

  @Override
  public Long saveDelivery(DeliveryModel deliveryModel) {
    deliveryModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    deliveryModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    return save(deliveryModel);
  }

  @Override
  public DeliveryModel findByDeliveryName(String name) {
    StringBuilder sql = new StringBuilder("FROM Delivery Where name=:name");
    DeliveryModel deliveryModel = new DeliveryModel();
    deliveryModel.setName(name);
    List<DeliveryModel> delivery = queryHibernate(sql.toString(), deliveryModel);
    return delivery.isEmpty() ? null : delivery.get(0);
  }

  @Override
  public DeliveryModel findByDeliveryID(Long id) {
    return findById(id);
  }

  @Override
  public void deleteDelivery(long id) {
    DeliveryModel deliveryModel = findById(id);
    delete(deliveryModel);
  }

  @Override
  public void update(DeliveryModel deliveryModel) {
    saveDelivery(deliveryModel);
  }


  @Override
  public int getTotalItem() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createSQLQuery("select count(*) from Delivery d");
    List<BigInteger> count1 = query.list();
    return count1.get(0).intValue();
  }
}
