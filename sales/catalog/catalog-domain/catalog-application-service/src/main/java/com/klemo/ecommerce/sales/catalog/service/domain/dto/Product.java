package com.klemo.ecommerce.sales.catalog.service.domain.dto;

import java.math.BigDecimal;

public record Product(
    String title,
    String description,
    BigDecimal price,
    String currency
) {
}
