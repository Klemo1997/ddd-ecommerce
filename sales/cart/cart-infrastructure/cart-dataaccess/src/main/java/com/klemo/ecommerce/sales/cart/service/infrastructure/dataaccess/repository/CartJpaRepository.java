package com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess.repository;

import com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, UUID> {
}
