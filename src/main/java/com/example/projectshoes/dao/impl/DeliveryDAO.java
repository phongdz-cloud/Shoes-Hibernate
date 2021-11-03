package com.example.projectshoes.dao.impl;

import com.example.projectshoes.dao.IDeliveryDAO;
import com.example.projectshoes.mapper.DeliveryMapper;
import com.example.projectshoes.model.DeliveryModel;
import java.util.List;

public class DeliveryDAO extends AbstractDAO<DeliveryModel> implements IDeliveryDAO {

  @Override
  public List<DeliveryModel> findAll() {
    StringBuilder sql = new StringBuilder("SELECT * FROM Delivery");
    return query(sql.toString(), new DeliveryMapper());
  }

  @Override
  public DeliveryModel findOne(Long id) {
    StringBuilder sql = new StringBuilder("SELECT * FROM Delivery Where id=?");
    List<DeliveryModel> deliveryModels = query(sql.toString(), new DeliveryMapper(), id);
    return deliveryModels.isEmpty() ? null : deliveryModels.get(0);
  }


  @Override
  public Long save(DeliveryModel deliveryModel) {
    return save(deliveryModel);
  }

  @Override
  public DeliveryModel findByDeliveryName(String name) {
    StringBuilder sql = new StringBuilder("SELECT * FROM Delivery Where name=?");
    sql.append(" VALUES(?)");
    List<DeliveryModel> delivery = query(sql.toString(), new DeliveryMapper(), name);
    return delivery.isEmpty() ? null : delivery.get(0);
  }

  @Override
  public DeliveryModel findByDeliveryID(Long id) {
    StringBuilder sql = new StringBuilder("SELECT * FROM Delivery Where id=?");
    sql.append(" VALUES(?)");
    List<DeliveryModel> delivery = query(sql.toString(), new DeliveryMapper(), id);
    return delivery.isEmpty() ? null : delivery.get(0);
  }

  @Override
  public void delete(long id) {
    DeliveryModel deliveryModel = findById(id);
    delete(deliveryModel);
  }

  @Override
  public void update(DeliveryModel deliveryModel) {
    update(deliveryModel);
  }

  @Override
  public List<DeliveryModel> PageDelivery(int page) {
    if (page < 1) {
      page = 1;
    }
    int offset = (page - 1) * 5;
    StringBuilder sql = new StringBuilder("select * from Delivery LIMIT 5 OFFSET ?");
    return query(sql.toString(), new DeliveryMapper(), offset);
  }

  @Override
  public int getTotalItem() {
    String sql = "SELECT count(*) FROM Delivery";
    return count(sql);
  }
}
