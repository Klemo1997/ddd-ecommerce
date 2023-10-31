package com.klemo.ecommerce.sales.cart.service.domain.dto;

import com.klemo.ecommerce.domain.value_object.CartId;
import lombok.NonNull;

public record ListCartItemsQuery(@NonNull CartId cartId) {
}
