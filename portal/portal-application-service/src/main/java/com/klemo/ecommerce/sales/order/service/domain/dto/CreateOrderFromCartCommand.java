package com.klemo.ecommerce.sales.order.service.domain.dto;

import java.util.UUID;

public record CreateOrderFromCartCommand(UUID cartId) {}
