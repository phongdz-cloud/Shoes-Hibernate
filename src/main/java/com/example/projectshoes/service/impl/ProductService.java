package com.example.projectshoes.service.impl;

import com.example.projectshoes.dao.IDeliveryDAO;
import com.example.projectshoes.dao.IProductDAO;
import com.example.projectshoes.dao.ISaledetailDAO;
import com.example.projectshoes.model.CartModel;
import com.example.projectshoes.model.DeliveryModel;
import com.example.projectshoes.model.LineItemModel;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.model.UserModel;
import com.example.projectshoes.paging.Pageble;
import com.example.projectshoes.service.IProductService;
import com.example.projectshoes.utils.SessionUtil;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class ProductService implements IProductService {

  @Inject
  IProductDAO productDAO;
  @Inject
  ISaledetailDAO saledetailDAO;
  @Inject
  IDeliveryDAO deliveryDAO;

  @Override
  public List<ProductModel> findAll(Pageble pageble, String key) {
    return productDAO.findAll(pageble, key);
  }

  @Override
  public Long saveProduct(ProductModel productModel) {
    productModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    productModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    return productDAO.saveProduct(productModel);
  }

  @Override
  public void deleteProduct(long[] ids) {
    for (long id : ids) {
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
  public List<ProductModel> findbyCategory(Pageble pageble, String code) {

    return productDAO.findbyCategory(pageble, code);
  }

  @Override
  public List<ProductModel> Sort(String sql, String categorycode) {
    StringBuilder query = new StringBuilder();
    if (!Objects.equals(categorycode, "")) {
      query.append("select p From Product p inner join p.category t");
      query.append(" where t.code='").append(categorycode).append("'");
    } else {
      query.append("FROM Product p");
    }
    if (sql.equals("A-Z")) {
      query.append(" ORDER BY p.name ASC");
    } else if (sql.equals("Price")) {
      query.append(" ORDER BY p.price");
    } else {
      query.append(" ORDER BY p.name DESC");
    }
    return productDAO.Sort(query.toString(), categorycode);
  }

  @Override
  public int getTotalItemByCategory(String code) {
    return productDAO.getTotalItemByCategory(code);
  }

  @Override
  public SaledetailModel UpdateAfertCheckout(HttpServletRequest req, UserModel userModel) {
    ProductModel productModel = new ProductModel();
    CartModel cart = (CartModel) SessionUtil.getInstance().getValue(req, "cart");
    SessionUtil.getInstance().putValue(req, "cart", cart);
    List<LineItemModel> lineItemModelList = cart.getItems();
    DeliveryModel deliveryModel = deliveryDAO.findOne(5L);
    SaledetailModel saledetailModel = null;
    String code = generateCode();
    for (LineItemModel item : lineItemModelList) {
      saledetailModel = new SaledetailModel();
      saledetailModel.setCode(code);
      productModel = productDAO.findOne(item.getProduct().getId());
      saledetailModel.setProduct(productModel);
      saledetailModel.setUser(userModel);
      saledetailModel.setQuantity(item.getQuantity());
      saledetailModel.setTotal((float) item.getTotal());
      saledetailModel.setCreatedBy(userModel.getUsername());
      saledetailModel.setDelivery(deliveryModel);
      saledetailModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
      saledetailDAO.saveSaledetail(saledetailModel);
      productModel.setQuantity(productModel.getQuantity() - item.getQuantity());
      productDAO.update(productModel);
    }
    return saledetailModel;
  }

  public String generateCode() {

    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
      int randomLimitedInt = leftLimit + (int)
          (random.nextFloat() * (rightLimit - leftLimit + 1));
      buffer.append((char) randomLimitedInt);
    }

    return buffer.toString();
  }

}