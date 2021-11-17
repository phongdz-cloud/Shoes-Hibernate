package com.example.projectshoes.service.impl;

import com.example.projectshoes.controller.Cart.CartModel;
import com.example.projectshoes.controller.Cart.LineItem;
import com.example.projectshoes.dao.IProductDAO;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.paging.Pageble;
import com.example.projectshoes.service.ICategoryService;
import com.example.projectshoes.service.IProductService;
import com.example.projectshoes.service.ISaledetailService;
import com.example.projectshoes.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

public class ProductService implements IProductService {

    @Inject
    IProductDAO productDAO;
    @Inject
    ICategoryService categoryService;
    @Inject
    ISaledetailService saledetailService;
    @Override
    public List<ProductModel> findAll(Pageble pageble,String key) {
        return productDAO.findAll(pageble,key);
    }

    @Override
    public Long saveProduct(ProductModel productModel) {
        productModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        productModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return productDAO.saveProduct(productModel);
    }

    @Override
    public void deleteProduct(long[] ids) {
        for(long id:ids){
            productDAO.deleteProduct(id);
        }
    }
    @Override
    public ProductModel findOne(Long id) {
        return productDAO.findOne(id);
    }
    @Override
    public void update(ProductModel productModel) {
        productModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        productDAO.update(productModel);
    }

    @Override
    public int getTotalItem() {
        return productDAO.getTotalItem();
    }

    @Override
    public List<ProductModel> findbyCategory(Pageble pageble,String code) {
        return productDAO.findbyCategory(pageble,code);
    }

    @Override
    public List<ProductModel> Sort(String sql,String categorycode) {
        StringBuilder query= new StringBuilder();
        if(categorycode!=""){
            query.append("select p From Product p inner join p.category t");
            query.append(" where t.code='"+categorycode+"'");
        }
        else {
            query.append("FROM Product p");
        }
        if(sql.equals("A-Z")){
            query.append(" ORDER BY p.name ASC");
        }
        else if(sql.equals("Price")){
            query.append(" ORDER BY p.price");
        }
        else {
            query.append(" ORDER BY p.name DESC");
        }
        return productDAO.Sort(query.toString(),categorycode);
    }

    @Override
    public int getTotalItemByCategory(String code) {
        return productDAO.getTotalItemByCategory(code);
    }

    @Override
    public void UpdateAfertCheckout(HttpServletRequest req) {
        ProductModel productModel=new ProductModel();
        CartModel cart = (CartModel) SessionUtil.getInstance().getValue(req,"cart");
        SessionUtil.getInstance().putValue(req,"cart",cart);
        List<LineItem> lineItemList=cart.getItems();
        for(LineItem item:lineItemList){
            productModel=productDAO.findOne(item.getProduct().getId());
//            SaledetailModel saledetailModel=new SaledetailModel();
//            saledetailModel.setProduct(item.getProduct());
//            saledetailModel.setUser();
            productModel.setQuantity(productModel.getQuantity()-item.getQuantity());
            productDAO.update(productModel);
        }
    }
}