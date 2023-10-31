package com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess.repository;

import com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
}
