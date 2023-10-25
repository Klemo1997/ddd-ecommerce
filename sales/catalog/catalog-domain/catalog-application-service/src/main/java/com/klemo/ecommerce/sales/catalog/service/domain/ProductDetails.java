package com.klemo.ecommerce.sales.catalog.service.domain;

import com.klemo.ecommerce.sales.catalog.service.domain.dto.ProductDetailsQuery;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ProductDetailsResponse;
import com.klemo.ecommerce.sales.catalog.service.domain.port.output.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductDetails {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDetailsResponse details(ProductDetailsQuery query) {
        Product product = productRepository.findProductById(query.productId())
                .orElseThrow(() -> {
                    log.warn("Could not find product with id: {}", query.productId());
                    return new ProductNotFoundException("Could not find product with id: " + query.productId());
                });
        return new ProductDetailsResponse(
            product.getTitle(),
            product.getDescription(),
            product.getPrice()
        );
    }
}
