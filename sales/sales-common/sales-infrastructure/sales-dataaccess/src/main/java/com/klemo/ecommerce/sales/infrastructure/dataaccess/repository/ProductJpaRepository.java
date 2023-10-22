package com.klemo.ecommerce.sales.infrastructure.dataaccess.repository;

import com.klemo.ecommerce.sales.infrastructure.dataaccess.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
}
