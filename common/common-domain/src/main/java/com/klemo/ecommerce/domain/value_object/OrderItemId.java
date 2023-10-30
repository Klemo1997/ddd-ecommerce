package com.klemo.ecommerce.domain.value_object;

import java.util.UUID;

public class OrderItemId extends Id<UUID> {
    public OrderItemId(UUID value) {
        super(value);
    }
}
