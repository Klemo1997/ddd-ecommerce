package com.klemo.ecommerce.infrastructure.kafka.model;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentRequestModel(
        @NonNull String id,
        @NonNull String orderId,
        @NonNull BigDecimal price,
        @NonNull String currency,
        @NonNull Instant createdAt) {}
