package com.klemo.ecommerce.sales.catalog.service.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDetailsResponse(
    UUID id,
    String title,
    String description,
    BigDecimal price,
    String currency
) {
}
