package com.example.projectshoes.service.impl;

import com.example.projectshoes.constant.SystemQueries;
import com.example.projectshoes.dao.ICacheDAO;
import com.example.projectshoes.dao.ISaledetailDAO;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.service.ISaledetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;

public class SaledetailService implements ISaledetailService {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Inject
  ISaledetailDAO saledetailDAO;
  @Inject
  ICacheDAO cacheDAO;


  @Override
  public List<SaledetailModel> findAll(int pageIndex) {
    return saledetailDAO.findAll(pageIndex);
  }

  @Override
  public Long saveSaledetail(SaledetailModel saledetailModel) {
    return saledetailDAO.saveSaledetail(saledetailModel);
  }

  @Override
  public List<SaledetailModel> findAllbyUserID(Long id, int pageIndex) {
    return saledetailDAO.findAllbyUserID(id, pageIndex);
  }


  @Override
  public void deleteSaledetail(long[] ids) {
    for (long id : ids) {
      saledetailDAO.deleteSaledetail(id);
      cacheDAO.deleteCache(SystemQueries.FINDSALEDETAILBYID + id);
    }
  }

  @Override
  public SaledetailModel findOne(Long id) {
    SaledetailModel saledetailModel = (SaledetailModel) cacheDAO.getObject(
        SystemQueries.FINDSALEDETAILBYID + id);
    if (saledetailModel == null) {
      saledetailModel = saledetailDAO.findOne(id);
      cacheDAO.setObject(SystemQueries.FINDSALEDETAILBYID + id, saledetailModel);
    }
    return saledetailModel;
  }

  @Override
  public void update(SaledetailModel saledetailModel) {
    saledetailModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    saledetailDAO.update(saledetailModel);
  }

  @Override
  public List<SaledetailModel> pageProduct(int page) {
    return saledetailDAO.PageSaledetail(page);
  }

  @Override
  public int getTotalItem() {
    return saledetailDAO.getTotalItem();
  }

  @Override
  public List<SaledetailModel> gettop3() {
    return saledetailDAO.gettop3();
  }

  @Override
  public void deletebyProductId(long[] ids) {
    for (long id : ids) {
      SaledetailModel saledetailModels = new SaledetailModel();
      saledetailModels.setListResult(saledetailDAO.findbyProductID(id));
      for (SaledetailModel i : saledetailModels.getListResult()) {
        saledetailDAO.delete(i);
        cacheDAO.deleteCache(SystemQueries.FINDSALEDETAILBYID + i.getId());
      }
    }
  }
}
