package com.example.projectshoes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItemModel {

  private ProductModel product;

  private int quantity;

  public double getTotal() {
    return product.getPrice() * quantity;
  }

}
