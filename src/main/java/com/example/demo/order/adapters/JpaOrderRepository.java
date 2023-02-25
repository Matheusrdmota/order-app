package com.example.demo.order.adapters;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderDataMapper, Long> {
    OrderDataMapper findByOrderNumber(Long orderNumber);
}
