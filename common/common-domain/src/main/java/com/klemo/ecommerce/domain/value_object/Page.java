package com.klemo.ecommerce.domain.value_object;

public record Page(int value) {
    public Page {
        if (value < 1) {
            throw new IllegalArgumentException("Page value must be larger than 1");
        }
    }
}
