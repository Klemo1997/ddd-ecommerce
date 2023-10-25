package com.klemo.ecommerce.sales.cart.service.domain.dto;

import java.util.List;

public record ListCartItemsResponse(List<CartItem> cartItemList) {
}
