package com.example.projectshoes.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Product")
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel extends AbstractModel<ProductModel> {

  @Column(name = "name")
  private String name;
  @Column(name = "price")
  private Float price;
  @Transient
  private Long categoryId;
  @Column(name = "size")
  private int size;
  @Column(name = "quantity")
  private int quantity;

  @Column(name = "avatar")
  @Lob
  private String avatar;

  @ManyToOne(targetEntity = CategoryModel.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private CategoryModel category;

  @ManyToOne(fetch = FetchType.LAZY)
  private StockModel stock;
  @OneToMany(mappedBy = "product")
  private List<SaledetailModel> saleDetails;

  public List<SaledetailModel> getSaleDetails() {
    if (this.saleDetails == null) {
      this.saleDetails = new ArrayList<>();
    }
    return saleDetails;
  }

}
