package com.klemo.ecommerce.domain.value_object;

public record Quantity(long value) {
    public Quantity {
        if (value < 0L) {
            throw new IllegalArgumentException("Value cannot be less than zero");
        }
    }

    public Quantity add(Quantity other) {
        return new Quantity(value + other.value);
    }
}
