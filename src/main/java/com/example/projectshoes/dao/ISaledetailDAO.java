package com.example.projectshoes.dao;

import com.example.projectshoes.model.SaledetailModel;
import java.util.List;

public interface ISaledetailDAO extends GenericDAO<SaledetailModel> {

  SaledetailModel findOne(Long id);

  Long saveSaledetail(SaledetailModel saledetailModel);

  List<SaledetailModel> findAll();


  List<SaledetailModel> findAll(int pageIndex);

  List<SaledetailModel> findAllbyUserID(Long id, int pageIndex);

  List<SaledetailModel> findAllByUserIdTotal(Long id);

  List<SaledetailModel> findbyProductID(Long id);

  List<SaledetailModel> findbyDeliveryID(Long id);

  List<SaledetailModel> gettop3();

  void deleteSaledetail(long id);

  void update(SaledetailModel saledetailModel);


  int getTotalItem();

  SaledetailModel findbyCode(String code);
}
