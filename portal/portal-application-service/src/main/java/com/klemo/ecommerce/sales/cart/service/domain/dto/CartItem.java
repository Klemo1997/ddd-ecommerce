package com.klemo.ecommerce.sales.cart.service.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItem(
    UUID productId,
    String title,
    BigDecimal unitPrice,
    String currency,
    Long quantity
) {
}
