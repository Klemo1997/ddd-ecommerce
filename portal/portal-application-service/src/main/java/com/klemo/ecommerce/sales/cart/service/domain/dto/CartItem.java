package com.klemo.ecommerce.sales.cart.service.domain.dto;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItem(
    @NonNull UUID productId,
    @NonNull String title,
    @NonNull BigDecimal unitPrice,
    @NonNull String currency,
    @NonNull Long quantity
) {
}
