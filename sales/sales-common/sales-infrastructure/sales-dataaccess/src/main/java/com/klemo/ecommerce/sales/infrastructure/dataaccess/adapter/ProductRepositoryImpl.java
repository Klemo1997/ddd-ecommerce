package com.klemo.ecommerce.sales.infrastructure.dataaccess.adapter;

import com.klemo.ecommerce.domain.value_object.Money;
import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.sales.domain.entity.Product;
import com.klemo.ecommerce.sales.domain.port.output.ProductRepository;
import com.klemo.ecommerce.sales.infrastructure.dataaccess.entity.ProductEntity;
import com.klemo.ecommerce.sales.infrastructure.dataaccess.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository jpaRepository;

    @Override
    public Optional<Product> findProductById(ProductId productId) {
        return jpaRepository.findById(productId.getValue())
                .map(this::productFromProductEntity);
    }

    private Product productFromProductEntity(ProductEntity productEntity) {
        return new Product(
            new ProductId(productEntity.getId()),
            productEntity.getTitle(),
            productEntity.getDescription(),
            Money.getInstance(productEntity.getUnitPrice(), productEntity.getCurrency())
        );
    }
}
