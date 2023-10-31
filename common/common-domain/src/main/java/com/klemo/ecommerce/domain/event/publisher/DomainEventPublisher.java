package com.klemo.ecommerce.domain.event.publisher;

import com.klemo.ecommerce.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
