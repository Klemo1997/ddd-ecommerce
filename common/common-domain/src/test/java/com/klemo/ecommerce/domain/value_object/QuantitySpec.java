package com.klemo.ecommerce.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantitySpec {
    @Test void shouldBeEqualIfValuesAreEqual() {
        assertEquals(new Quantity(1), new Quantity(1));
    }

    @Test void shouldNotBeEqualIfValuesAreEqual() {
        assertNotEquals(new Quantity(1), new Quantity(2));
    }

    @Test void shouldThrowIllegalArgumentWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
    }

    @Test void add_shouldProduceSum() {
        final Quantity expected = new Quantity(3);

        final Quantity first = new Quantity(1);
        final Quantity second = new Quantity(2);
        assertEquals(expected, first.add(second));
    }
}
