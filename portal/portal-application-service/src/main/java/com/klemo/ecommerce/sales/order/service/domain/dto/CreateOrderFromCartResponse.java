package com.klemo.ecommerce.sales.order.service.domain.dto;

import com.klemo.ecommerce.sales.order.service.domain.value_object.OrderStatus;
import lombok.NonNull;

import java.util.UUID;

public record CreateOrderFromCartResponse(
        @NonNull UUID trackingId,
        @NonNull OrderStatus orderStatus) {}
