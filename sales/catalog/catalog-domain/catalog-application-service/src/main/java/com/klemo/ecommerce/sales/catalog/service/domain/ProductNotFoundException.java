package com.klemo.ecommerce.sales.catalog.service.domain;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
