package com.klemo.ecommerce.sales.catalog.service.domain;

import com.klemo.ecommerce.domain.value_object.Quantity;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ProductDetailsQuery;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ProductDetailsResponse;
import com.klemo.ecommerce.sales.catalog.service.domain.port.output.ProductRepository;
import com.klemo.ecommerce.warehouse.service.domain.entity.Stock;
import com.klemo.ecommerce.warehouse.service.domain.port.output.WarehouseRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductDetails {
    @NonNull private final ProductRepository productRepository;
    @NonNull private final WarehouseRepository warehouseRepository;

    @Transactional(readOnly = true)
    public ProductDetailsResponse details(@NonNull ProductDetailsQuery query) {
        Product product = productRepository.findProductById(query.productId())
                .orElseThrow(() -> {
                    log.warn("Could not find product with id: {}", query.productId());
                    return new ProductNotFoundException("Could not find product with id: " + query.productId());
                });
        Optional<Stock> productStock = warehouseRepository.findStockByProductId(query.productId());
        return new ProductDetailsResponse(
            product.getId().getValue(),
            product.getTitle(),
            product.getDescription(),
            product.getPrice().amount(),
            product.getPrice().currency().getCurrencyCode(),
            productStock.map(Stock::getQuantity).orElse(Quantity.ZERO).value()
        );
    }
}
