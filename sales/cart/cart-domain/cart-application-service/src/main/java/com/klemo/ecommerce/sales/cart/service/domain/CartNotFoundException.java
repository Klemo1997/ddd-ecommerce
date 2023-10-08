package com.klemo.ecommerce.sales.cart.service.domain;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message) {
        super(message);
    }
}
