package com.klemo.ecommerce.sales.cart.service.domain.dto;

import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public record AddProductToCartResponse(@NonNull UUID cartId, @NonNull List<CartItem> items) {
}
