package com.example.orders.model;

import lombok.Data;

@Data
public class OrderItem {
  private Long productId;
  private Integer qty;
  private Double price;

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long p) {
    this.productId = p;
  }

  public Integer getQty() {
    return qty;
  }

  public void setQty(Integer q) {
    this.qty = q;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double p) {
    this.price = p;
  }
}
