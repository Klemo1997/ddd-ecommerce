package com.klemo.ecommerce.sales.cart.service.domain;

import lombok.NonNull;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(@NonNull String message) {
        super(message);
    }
}
