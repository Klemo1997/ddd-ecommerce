package com.klemo.ecommerce.sales.catalog.service.domain.dto;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDetailsResponse(
    @NonNull UUID id,
    @NonNull String title,
    @NonNull String description,
    @NonNull BigDecimal price,
    @NonNull String currency
) {
}
