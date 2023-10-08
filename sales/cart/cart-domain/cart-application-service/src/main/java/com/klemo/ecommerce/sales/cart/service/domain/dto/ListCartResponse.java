package com.klemo.ecommerce.sales.cart.service.domain.dto;

import com.klemo.ecommerce.sales.cart.service.domain.entity.CartItem;

import java.util.List;

public record ListCartResponse(List<CartItem> cartItemList) {
}
