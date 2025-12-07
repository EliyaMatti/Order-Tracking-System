package com.example.orders.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_uuid", unique = true)
  private String orderUuid = UUID.randomUUID().toString();

  private Long userId;
  private Double totalAmount;
  private String status;
  private Instant createdAt = Instant.now();

  public Long getId() {
    return id;
  }

  public String getOrderUuid() {
    return orderUuid;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long u) {
    this.userId = u;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double t) {
    this.totalAmount = t;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String s) {
    this.status = s;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }
}
