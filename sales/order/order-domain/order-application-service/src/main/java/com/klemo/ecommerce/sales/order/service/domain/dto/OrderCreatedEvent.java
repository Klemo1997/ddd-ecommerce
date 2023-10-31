package com.klemo.ecommerce.sales.order.service.domain.dto;

import com.klemo.ecommerce.domain.event.DomainEvent;
import com.klemo.ecommerce.sales.order.service.domain.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Getter
public class OrderCreatedEvent implements DomainEvent {
    private final Order order;
    private final ZonedDateTime createdAt;
}
