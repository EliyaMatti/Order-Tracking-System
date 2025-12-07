package com.example.orders.service;

import com.example.orders.kafka.OrderCreatedEvent;
import com.example.orders.kafka.OrderProducer;
import com.example.orders.model.CreateOrderRequest;
import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
  private final OrderRepository repo;
  private final OrderProducer producer;

  public OrderService(OrderRepository repo, OrderProducer producer) {
    this.repo = repo;
    this.producer = producer;
  }

  @Transactional
  public String createOrder(CreateOrderRequest req) {
    Order order =
        Order.builder()
            .userId(req.getUserId())
            .totalAmount(req.getItems().stream().mapToDouble(e -> e.getQty() * e.getPrice()).sum())
            .orderUuid(UUID.randomUUID().toString())
            .createdAt(Instant.now())
            .status("PENDING")
            .build();
    repo.save(order);

    var orderCreatedEvent = new OrderCreatedEvent();
    orderCreatedEvent.orderId = order.getOrderUuid();
    orderCreatedEvent.userId = order.getUserId();
    orderCreatedEvent.totalAmount = order.getTotalAmount();
    orderCreatedEvent.items = req.getItems();
    orderCreatedEvent.paymentMethod = req.getPaymentMethod();
    producer.sendOrder(orderCreatedEvent);
    return order.getOrderUuid();
  }

  public Order getOrder(String orderId) {
    return repo.findByOrderUuid(orderId).orElseThrow();
  }
}
