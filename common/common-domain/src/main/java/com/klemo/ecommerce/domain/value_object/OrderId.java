package com.klemo.ecommerce.domain.value_object;

import java.util.UUID;

public class OrderId extends Id<UUID> {
    public OrderId(UUID value) {
        super(value);
    }
}
