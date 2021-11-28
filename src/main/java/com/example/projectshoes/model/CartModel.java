package com.example.projectshoes.model;

import java.util.ArrayList;

public class CartModel {

  private ArrayList<LineItemModel> items;

  public CartModel() {
    items = new ArrayList<LineItemModel>();
  }

  public ArrayList<LineItemModel> getItems() {
    return items;
  }

  public int getCount() {
    return items.size();
  }

  public void addItem(LineItemModel item) {
    String code = item.getProduct().getName();
    int quantity = item.getQuantity();
    for (LineItemModel cartItem : items) {
      if (cartItem.getProduct().getName().equals(code)) {
        cartItem.setQuantity(quantity);
        return;
      }
    }
    items.add(item);
  }

  public void removeItem(LineItemModel item) {
    String code = item.getProduct().getName();
    for (int i = 0; i < items.size(); i++) {
      LineItemModel lineItemModel = items.get(i);
      if (lineItemModel.getProduct().getName().equals(code)) {
        items.remove(i);
        return;
      }
    }
  }

  public double totalPrice(LineItemModel item) {
    double total = 0;
    for (int i = 0; i < items.size(); i++) {
      LineItemModel lineItemModel = items.get(i);
      total += lineItemModel.getTotal();
    }
    return total;
  }
}
