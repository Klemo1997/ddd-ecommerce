package com.klemo.ecommerce.warehouse.service.infrastructure.dataaccess.adapter;

import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.domain.value_object.Quantity;
import com.klemo.ecommerce.warehouse.service.domain.entity.Stock;
import com.klemo.ecommerce.warehouse.service.domain.port.output.WarehouseRepository;
import com.klemo.ecommerce.warehouse.service.infrastructure.dataaccess.entity.StockEntity;
import com.klemo.ecommerce.warehouse.service.infrastructure.dataaccess.repository.StockJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WarehouseRepositoryImpl implements WarehouseRepository {
    private final StockJpaRepository stockJpaRepository;

    @Override
    public Optional<Stock> findStockByProductId(ProductId productId) {
        return stockJpaRepository.findById(productId.getValue()).map(this::stockEntityToStock);
    }

    private Stock stockEntityToStock(StockEntity stockEntity) {
        return new Stock(new ProductId(stockEntity.getProductId()), new Quantity(stockEntity.getQuantity()));
    }
}
