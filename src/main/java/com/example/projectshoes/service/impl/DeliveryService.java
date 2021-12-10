package com.example.projectshoes.service.impl;

import com.example.projectshoes.constant.SystemQueries;
import com.example.projectshoes.dao.ICacheDAO;
import com.example.projectshoes.dao.IDeliveryDAO;
import com.example.projectshoes.model.DeliveryModel;
import com.example.projectshoes.service.IDeliveryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;

public class DeliveryService implements IDeliveryService {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Inject
  IDeliveryDAO deliveryDAO;
  @Inject
  ICacheDAO cacheDAO;

  @Override
  public List<DeliveryModel> findAll() {
    List<DeliveryModel> results = null;
    try {
      String jsonString = (String) cacheDAO.getObject(SystemQueries.FINDALLDELIVERY);
      if (jsonString == null) {
        results = deliveryDAO.findAll();
        cacheDAO.setObject(SystemQueries.FINDALLDELIVERY, objectMapper.writeValueAsString(results));
      } else {
        results = objectMapper.readValue(jsonString, new TypeReference<DeliveryModel>() {
        });
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return results;
  }

  @Override
  public Long saveDelivery(DeliveryModel deliveryModel) {
    deliveryModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    deliveryModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    return deliveryDAO.save(deliveryModel);
  }

  @Override
  public DeliveryModel findByDeliveryName(String name) {
    DeliveryModel deliveryModel = (DeliveryModel) cacheDAO.getObject(
        SystemQueries.FINDDELIVERYBYNAME + name);
    if (deliveryModel == null) {
      deliveryModel = deliveryDAO.findByDeliveryName(name);
      cacheDAO.setObject(SystemQueries.FINDDELIVERYBYNAME + name, deliveryModel);
    }
    return deliveryModel;
  }

  @Override
  public DeliveryModel findByDeliveryID(Long id) {
    DeliveryModel deliveryModel = (DeliveryModel) cacheDAO.getObject(
        SystemQueries.FINDDELIVERYBYID + id);
    if (deliveryModel == null) {
      deliveryModel = deliveryDAO.findByDeliveryID(id);
      cacheDAO.setObject(SystemQueries.FINDDELIVERYBYID + id, deliveryModel);
    }
    return deliveryModel;
  }

  @Override
  public void deleteDelivery(long[] ids) {
    for (long id : ids) {
      deliveryDAO.deleteDelivery(id);
      cacheDAO.deleteCache(SystemQueries.FINDDELIVERYBYID + id);
    }
  }

  @Override
  public DeliveryModel findOne(Long id) {
    DeliveryModel deliveryModel = (DeliveryModel) cacheDAO.getObject(
        SystemQueries.FINDDELIVERYBYID + id);
    if (deliveryModel == null) {
      deliveryModel = deliveryDAO.findOne(id);
      cacheDAO.setObject(SystemQueries.FINDDELIVERYBYID + id, deliveryModel);
    }
    return deliveryModel;
  }

  @Override
  public void update(DeliveryModel deliveryModel) {
    deliveryModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    deliveryDAO.update(deliveryModel);
    cacheDAO.updateFindALL(SystemQueries.FINDALLDELIVERY, deliveryDAO.findAll());
    cacheDAO.updateFindBy(SystemQueries.FINDDELIVERYBYID + deliveryModel.getId(), deliveryModel);
    cacheDAO.updateFindBy(SystemQueries.FINDDELIVERYBYNAME + deliveryModel.getName(),
        deliveryModel);
  }

  @Override
  public List<DeliveryModel> pageDelivery(int page) {
    return deliveryDAO.PageDelivery(page);
  }

  @Override
  public int getTotalItem() {
    return deliveryDAO.getTotalItem();
  }
}
