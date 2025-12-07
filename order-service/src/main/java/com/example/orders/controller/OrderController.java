package com.example.orders.controller;

import com.example.orders.model.CreateOrderRequest;
import com.example.orders.service.OrderService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) {
    var orderID = orderService.createOrder(request);
    return ResponseEntity.status(201).body(Map.of("OderId", orderID));
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<?> getOrder(@PathVariable("orderId") String orderId) {
    return ResponseEntity.ok(orderService.getOrder(orderId));
  }
}
