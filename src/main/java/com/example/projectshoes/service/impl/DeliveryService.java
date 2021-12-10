package com.example.projectshoes.service.impl;

import com.example.projectshoes.dao.IDeliveryDAO;
import com.example.projectshoes.model.DeliveryModel;
import com.example.projectshoes.service.IDeliveryService;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;

public class DeliveryService implements IDeliveryService {


  @Inject
  IDeliveryDAO deliveryDAO;

  @Override
  public List<DeliveryModel> findAll() {
    return deliveryDAO.findAll();
  }

  @Override
  public Long saveDelivery(DeliveryModel deliveryModel) {
    deliveryModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    deliveryModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    return deliveryDAO.save(deliveryModel);
  }

  @Override
  public void deleteDelivery(long[] ids) {
    for (long id : ids) {
      deliveryDAO.deleteDelivery(id);
    }
  }

  @Override
  public DeliveryModel findOne(Long id) {
    return deliveryDAO.findOne(id);
  }

  @Override
  public void update(DeliveryModel deliveryModel) {
    deliveryModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    deliveryDAO.update(deliveryModel);
  }


  @Override
  public int getTotalItem() {
    return deliveryDAO.getTotalItem();
  }
}
