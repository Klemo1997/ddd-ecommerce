package com.klemo.ecommerce.sales.cart.service.domain.dto;

import java.util.List;
import java.util.UUID;

public record AddCartItemResponse(UUID cartId, List<CartItem> items) {
}
