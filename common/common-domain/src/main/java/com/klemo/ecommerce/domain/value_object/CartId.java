package com.klemo.ecommerce.domain.value_object;

import java.util.UUID;

public final class CartId extends Id<UUID> {
    public CartId(UUID value) {
        super(value);
    }
}
