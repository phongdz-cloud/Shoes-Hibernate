package com.example.projectshoes.service.impl;

import com.example.projectshoes.dao.ISaledetailDAO;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.paging.Pageble;
import com.example.projectshoes.service.ISaledetailService;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;

public class SaledetailService implements ISaledetailService {

  @Inject
  ISaledetailDAO saledetailDAO;


  @Override
  public List<SaledetailModel> findAll() {
    return saledetailDAO.findAll();
  }

  @Override
  public Long saveSaledetail(SaledetailModel saledetailModel) {
    saledetailModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    saledetailModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    return saledetailDAO.saveSaledetail(saledetailModel);
  }

  @Override
  public List<SaledetailModel> findAllbyUserID(Long id,int pageIndex) {
    return saledetailDAO.findAllbyUserID(id,pageIndex);
  }


  @Override
  public void deleteSaledetail(long[] ids) {
    for (long id : ids) {
      saledetailDAO.deleteSaledetail(id);
    }
  }

  @Override
  public SaledetailModel findOne(Long id) {
    return saledetailDAO.findOne(id);
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
}
