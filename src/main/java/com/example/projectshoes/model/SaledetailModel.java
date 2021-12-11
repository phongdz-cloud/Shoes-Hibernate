package com.example.projectshoes.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "Saledetail")
@Table(name = "saledetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaledetailModel extends AbstractModel<SaledetailModel> {

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


}
