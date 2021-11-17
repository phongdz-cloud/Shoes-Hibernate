package com.example.projectshoes.service.impl;

import com.example.projectshoes.constant.SystemConstant;
import com.example.projectshoes.dao.ISaledetailDAO;
import com.example.projectshoes.model.DeliveryModel;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.service.IDeliveryService;
import com.example.projectshoes.service.IProductService;
import com.example.projectshoes.service.ISaledetailService;
import com.example.projectshoes.service.IUserService;
import com.example.projectshoes.utils.JavaMailUtil;
import com.example.projectshoes.utils.PathUtil;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class SaledetailService implements ISaledetailService {
    @Inject
    ISaledetailDAO saledetailDAO;
    @Inject
    IUserService userService;
    @Inject
    IProductService productService;
    @Inject
    IDeliveryService deliveryService;

    @Override
    public List<SaledetailModel> findAll() {
        return saledetailDAO.findAll();
    }

    @Override
    public Long saveSaledetail(SaledetailModel saledetailModel) {
        saledetailModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        saledetailModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        UserModel userModel=userService.findByUserID(saledetailModel.getUserId());
        ProductModel productModel=productService.findOne(saledetailModel.getProductId());
        DeliveryModel deliveryModel=deliveryService.findByDeliveryID(saledetailModel.getDeliveryId());
        saledetailModel.setUser(userModel);
        saledetailModel.setProduct(productModel);
        saledetailModel.setDelivery(deliveryModel);
        return saledetailDAO.saveSaledetail(saledetailModel);
    }


    @Override
    public void deleteSaledetail(long[] ids) {
        for(long id:ids){
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
