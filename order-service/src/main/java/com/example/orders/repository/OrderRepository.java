package com.example.orders.repository;

import com.example.orders.model.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
  Optional<Order> findByOrderUuid(String orderUuid);
}
