package com.klemo.ecommerce.sales.catalog.service.domain.port.output;

import com.klemo.ecommerce.domain.value_object.Page;
import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.sales.catalog.service.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findProductById(ProductId productId);

    List<Product> findProducts(Page page);
}
