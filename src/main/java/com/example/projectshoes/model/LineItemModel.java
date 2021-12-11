package com.example.projectshoes.model;

import java.text.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineItemModel {

  private ProductModel product;

  private int quantity;

  public double getTotal() {
    return product.getPrice() * quantity;
  }

  public String getTotalCurrencyFormat(){
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    return currency.format(this.getTotal());
  }
}
