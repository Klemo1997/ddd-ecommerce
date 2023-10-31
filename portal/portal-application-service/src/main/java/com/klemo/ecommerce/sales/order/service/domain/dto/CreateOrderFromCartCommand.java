package com.klemo.ecommerce.sales.order.service.domain.dto;

import lombok.NonNull;

import java.util.UUID;

public record CreateOrderFromCartCommand(
        @NonNull UUID cartId,
        @NonNull OrderAddress address) {}