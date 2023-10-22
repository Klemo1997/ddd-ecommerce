package com.klemo.ecommerce.sales.cart.service.domain.dto;

import com.klemo.ecommerce.sales.cart.service.domain.entity.CartItem;

import java.util.List;

// exposes domain entity to world, must be fixed..
public record ListCartItemsResponse(List<CartItem> cartItemList) {
}
