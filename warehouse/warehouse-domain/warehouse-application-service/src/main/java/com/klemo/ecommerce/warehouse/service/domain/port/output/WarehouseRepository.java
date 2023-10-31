package com.klemo.ecommerce.warehouse.service.domain.port.output;

import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.warehouse.service.domain.entity.Stock;

import java.util.Optional;

public interface WarehouseRepository {
    Optional<Stock> findStockByProductId(ProductId productId);
}
