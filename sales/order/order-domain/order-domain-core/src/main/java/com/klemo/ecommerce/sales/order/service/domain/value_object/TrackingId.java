package com.klemo.ecommerce.sales.order.service.domain.value_object;

import com.klemo.ecommerce.domain.value_object.Id;

import java.util.UUID;

public class TrackingId extends Id<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
