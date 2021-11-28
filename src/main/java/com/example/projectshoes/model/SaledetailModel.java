package com.example.projectshoes.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Saledetail")
@Table(name = "saledetail")
public class SaledetailModel extends AbstractModel<SaledetailModel> implements Serializable {

  @Column(name = "quantity")
  private int quantity;
  @Column(name = "total")
  private Float total;

  @Column(name = "code")
  private String code;

  @Column(name = "status_delivery")
  private String status_delivery;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_user")
  private UserModel user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_delivery")
  private DeliveryModel delivery;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_product")
  private ProductModel product;

  public SaledetailModel() {
  }


  public void setDelivery(DeliveryModel delivery) {
    this.delivery = delivery;
  }


  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Float getTotal() {
    return total;
  }

  public void setTotal(Float total) {
    this.total = total;
  }

  public String getStatus_delivery() {
    return status_delivery;
  }

  public void setStatus_delivery(String status_delivery) {
    this.status_delivery = status_delivery;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ProductModel getProduct() {
    return product;
  }

  public void setProduct(ProductModel product) {
    this.product = product;
  }

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }



  public DeliveryModel getDelivery() {
    return delivery;
  }


}
