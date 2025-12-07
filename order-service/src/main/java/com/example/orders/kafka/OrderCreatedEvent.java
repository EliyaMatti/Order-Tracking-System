package com.example.orders.kafka;

import com.example.orders.model.OrderItem;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class OrderCreatedEvent {
  public UUID eventId = UUID.randomUUID();
  public String eventType = "OrderCreated";
  public Instant occurredAt = Instant.now();
  public String orderId;
  public Long userId;
  public List<OrderItem> items;
  public Double totalAmount;
  public String paymentMethod;
}
