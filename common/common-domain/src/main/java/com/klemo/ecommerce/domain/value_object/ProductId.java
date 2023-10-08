package com.klemo.ecommerce.domain.value_object;

import java.util.UUID;

public class ProductId extends Id<UUID> {
    public ProductId(UUID value) {
        super(value);
    }
}
