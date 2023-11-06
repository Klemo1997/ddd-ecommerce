package com.klemo.ecommerce.payment.service.domain.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentRequest(UUID id, UUID orderId, BigDecimal price, Instant createdAt) {
}
