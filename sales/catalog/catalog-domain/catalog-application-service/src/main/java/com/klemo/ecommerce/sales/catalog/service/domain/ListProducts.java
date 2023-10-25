package com.klemo.ecommerce.sales.catalog.service.domain;

import com.klemo.ecommerce.domain.value_object.Page;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ListProductsQuery;
import com.klemo.ecommerce.sales.catalog.service.domain.dto.ListProductsResponse;
import com.klemo.ecommerce.sales.catalog.service.domain.port.output.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.klemo.ecommerce.sales.catalog.service.domain.Product;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListProducts {
    @NonNull private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ListProductsResponse list(@NonNull ListProductsQuery query) {
        List<Product> productsList = productRepository.findProducts(new Page(query.page()));
        return new ListProductsResponse(productsList.stream().map(this::productToProductDto).toList());
    }

    private com.klemo.ecommerce.sales.catalog.service.domain.dto.Product productToProductDto(Product product) {
        return new com.klemo.ecommerce.sales.catalog.service.domain.dto.Product(
                product.getId().getValue(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice().amount(),
                product.getPrice().currency().getCurrencyCode()
        );
    }
}
