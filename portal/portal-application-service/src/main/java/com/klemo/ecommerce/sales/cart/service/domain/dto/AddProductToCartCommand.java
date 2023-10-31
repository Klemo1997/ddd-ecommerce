package com.klemo.ecommerce.sales.cart.service.domain.dto;

import lombok.NonNull;

import java.util.UUID;

public record AddProductToCartCommand(@NonNull UUID productId, @NonNull Long quantity) {
}
