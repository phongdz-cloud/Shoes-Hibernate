package com.example.projectshoes.model;


import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Delivery")
@Table(name = "delivery")
public class DeliveryModel extends AbstractModel<DeliveryModel> implements Serializable {

  @Column(name = "shipper")
  private String shipper;
  @Column(name = "status")
  private String status;
  @Column(name = "deliverydate")
  private Date deliveryDate;
  @Column(name = "name")
  private String name;
  @OneToMany(mappedBy = "delivery")
  private List<SaledetailModel> saleDetails;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShipper() {
    return shipper;
  }

  public void setShipper(String shipper) {
    this.shipper = shipper;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(Date deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public List<SaledetailModel> getSaleDetails() {
    if (this.saleDetails == null) {
      this.saleDetails = new ArrayList<>();
    }
    return saleDetails;
  }

  public void setSaleDetails(List<SaledetailModel> saleDetails) {
    this.saleDetails = saleDetails;
  }
}
