package com.example.projectshoes.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity(name = "Saledetail")
@Table(name = "saledetail", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "product_id"
    }),
    @UniqueConstraint(columnNames = {
        "user_id"
    }),
    @UniqueConstraint(columnNames = {
        "delivery_id"
    })
})
public class
SaledetailModel extends AbstractModel<SaledetailModel> implements Serializable {

  //  @Column(name = "user_id")
  @Transient
  private Long userId;
  //  @Column(name = "product_id")
  @Transient
  private Long productId;
  //  @Column(name = "delivery_id")
  @Transient
  private Long deliveryId;
  @Column(name = "quantity")
  private int quantity;
  @Column(name = "total")
  private Float total;
  @Column(name = "status_delivery")
  private String status_delivery;
  @OneToOne(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserModel user = new UserModel();
  @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinColumn(name = "delivery_id", referencedColumnName = "id")
  private DeliveryModel delivery = new DeliveryModel();
  @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private ProductModel product = new ProductModel();

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

  public DeliveryModel getDelivery() {
    return delivery;
  }

  public void setDelivery(DeliveryModel delivery) {
    this.delivery = delivery;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getDeliveryId() {
    return deliveryId;
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

  public void setDeliveryId(Long deliveryId) {
    this.deliveryId = deliveryId;
  }

  public ProductModel getProduct() {
    return product;
  }

  public void setProduct(ProductModel product) {
    this.product = product;
  }
}
