package com.klemo.ecommerce.sales.catalog.service.infrastructure.dataaccess.adapter;

import com.klemo.ecommerce.domain.value_object.Money;
import com.klemo.ecommerce.domain.value_object.Page;
import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.sales.catalog.service.domain.Product;
import com.klemo.ecommerce.sales.catalog.service.domain.port.output.ProductRepository;
import com.klemo.ecommerce.sales.catalog.service.infrastructure.dataaccess.entity.ProductEntity;
import com.klemo.ecommerce.sales.catalog.service.infrastructure.dataaccess.repository.ProductJpaRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    @NonNull private final ProductJpaRepository jpaRepository;

    private static final int PAGE_SIZE = 10;
    private static final int PAGE_OFFSET = -1;

    @Override
    public Optional<Product> findProductById(@NonNull ProductId productId) {
        return jpaRepository.findById(productId.getValue())
                .map(this::productFromProductEntity);
    }

    @Override
    public List<Product> findProducts(@NonNull Page page) {
        return jpaRepository
                .findAll(Pageable.ofSize(PAGE_SIZE).withPage(page.value() + PAGE_OFFSET))
                .map(this::productFromProductEntity)
                .toList();
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
