package com.klemo.ecommerce.domain.value_object;

public record Quantity(long value) implements Comparable<Quantity> {
    public static Quantity ZERO = new Quantity(0L);

    public Quantity {
        if (value < 0L) {
            throw new IllegalArgumentException("Value cannot be less than zero");
        }
    }

    public Quantity add(Quantity other) {
        return new Quantity(value + other.value);
    }

    public Quantity subtract(Quantity other) {
        return new Quantity(value - other.value);
    }

    @Override
    public int compareTo(Quantity o) {
        if (o == null) throw new NullPointerException();

        return Long.compare(value, o.value);
    }

    public boolean isEmpty() {
        return equals(ZERO);
    }
}
