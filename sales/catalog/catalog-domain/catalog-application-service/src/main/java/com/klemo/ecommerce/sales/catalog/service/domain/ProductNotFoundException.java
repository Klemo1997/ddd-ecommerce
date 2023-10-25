package com.klemo.ecommerce.sales.catalog.service.domain;

import lombok.NonNull;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(@NonNull String message) {
        super(message);
    }
}
