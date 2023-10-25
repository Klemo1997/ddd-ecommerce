package com.klemo.ecommerce.sales.catalog.service.domain.dto;

import com.klemo.ecommerce.domain.value_object.ProductId;
import lombok.NonNull;

public record ProductDetailsQuery(@NonNull ProductId productId) {
}
