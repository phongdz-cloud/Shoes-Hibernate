package com.example.projectshoes.service;

import com.example.projectshoes.model.SaledetailModel;

import com.example.projectshoes.paging.Pageble;
import java.util.List;

public interface ISaledetailService {
    List<SaledetailModel> findAll();
    Long saveSaledetail(SaledetailModel saledetailModel);
    List<SaledetailModel> findAllbyUserID(Long id,int pageIndex);
    void deleteSaledetail(long[] ids);
    SaledetailModel findOne(Long id);
    void update(SaledetailModel saledetailModel);
    List<SaledetailModel> pageProduct(int page);
    int getTotalItem();
}
