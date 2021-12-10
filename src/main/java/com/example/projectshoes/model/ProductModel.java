package com.example.projectshoes.model;

import java.io.Serializable;
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

@Entity(name = "Product")
@Table(name = "product")
public class ProductModel extends AbstractModel<ProductModel> implements Serializable {

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

  @Override
  public String getAvatar() {
    return avatar;
  }

  @Override
  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

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

  public CategoryModel getCategory() {
    return category;
  }

  public void setCategory(CategoryModel category) {
    this.category = category;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public StockModel getStock() {
    return stock;
  }

  public void setStock(StockModel stock) {
    this.stock = stock;
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
