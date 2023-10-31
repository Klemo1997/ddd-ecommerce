package com.klemo.ecommerce.warehouse.service.infrastructure.dataaccess.repository;

import com.klemo.ecommerce.warehouse.service.infrastructure.dataaccess.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, UUID> {
}
