package com.klemo.ecommerce.sales.domain.port.output;

import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.sales.domain.entity.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findProductById(ProductId productId);
}
