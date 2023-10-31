package com.klemo.ecommerce.sales.order.service.domain.dto;

import com.klemo.ecommerce.domain.value_object.CartId;

public record CreateOrderFromCartCommand(CartId cartId) {}
