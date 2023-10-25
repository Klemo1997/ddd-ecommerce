package com.klemo.ecommerce.sales.cart.service.domain.dto;

import java.util.UUID;

public record AddProductToCartCommand(UUID productId, Long quantity) {
}
