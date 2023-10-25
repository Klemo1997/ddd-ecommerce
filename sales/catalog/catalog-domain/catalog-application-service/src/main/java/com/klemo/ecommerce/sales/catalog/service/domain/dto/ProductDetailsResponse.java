package com.klemo.ecommerce.sales.catalog.service.domain.dto;

import com.klemo.ecommerce.domain.value_object.Money;

public record ProductDetailsResponse(
    String title,
    String description,
    Money price
) {
}
