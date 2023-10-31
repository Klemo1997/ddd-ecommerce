package com.klemo.ecommerce.sales.order.service.domain.port.output;

import com.klemo.ecommerce.domain.event.publisher.DomainEventPublisher;
import com.klemo.ecommerce.sales.order.service.domain.dto.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestEventPublisher extends DomainEventPublisher<OrderCreatedEvent> {}
