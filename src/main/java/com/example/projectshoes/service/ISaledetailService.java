package com.example.projectshoes.service;

import com.example.projectshoes.model.SaledetailModel;

import com.example.projectshoes.paging.Pageble;
import java.util.List;

public interface ISaledetailService {
    List<SaledetailModel> findAll(int pageIndex);
    Long saveSaledetail(SaledetailModel saledetailModel);
    List<SaledetailModel> findAllbyUserID(Long id,int pageIndex);
    void deleteSaledetail(long[] ids);
    SaledetailModel findOne(Long id);
    void update(SaledetailModel saledetailModel);
    int getTotalItem();
    List<SaledetailModel> gettop3();
    void deletebyProductId(long[] ids);
}
