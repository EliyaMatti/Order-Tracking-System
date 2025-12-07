package com.example.orders.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public OrderProducer(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendOrder(OrderCreatedEvent orderCreatedEvent) {
    kafkaTemplate.send("Order-Created", orderCreatedEvent.orderId, orderCreatedEvent);

  }
}
