package com.example.projectshoes.model;


import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Delivery")
@Table(name = "delivery")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryModel extends AbstractModel<DeliveryModel> {

  @Column(name = "shipper")
  private String shipper;
  @Column(name = "status")
  private String status;
  @Column(name = "deliverydate")
  private Date deliveryDate;
  @Column(name = "name")
  private String name;
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "delivery")
  private List<SaledetailModel> saleDetails;

}
