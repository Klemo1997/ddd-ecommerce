package com.klemo.ecommerce.sales.cart.service.domain.dto;

import lombok.NonNull;

import java.util.List;

public record ListCartItemsResponse(@NonNull List<CartItem> cartItemList) {
}
