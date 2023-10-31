package com.klemo.ecommerce.domain.value_object;

import java.util.UUID;

public class CartItemId extends Id<UUID> {
    public CartItemId(UUID value) {
        super(value);
    }
}
