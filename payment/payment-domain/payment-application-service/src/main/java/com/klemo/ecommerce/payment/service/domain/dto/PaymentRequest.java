package com.klemo.ecommerce.payment.service.domain.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentRequest(String id, String orderId, BigDecimal price, Instant createdAt) {
}
