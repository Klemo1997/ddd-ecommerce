package com.klemo.ecommerce.domain.value_object;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public record Money(long cents, Currency currency) {
    private static final List<Integer> centMultiplier = List.of(1, 10, 100, 1000);

    public Money {
        checkCurrency(currency);
    }

    public static Money getInstance(long amount, Currency currency) {
        checkCurrency(currency);
        long cents = amount * centMultiplier.get(currency.getDefaultFractionDigits());
        return new Money(cents, currency);
    }

    public static Money getInstance(BigDecimal amount, Currency currency) {
        checkCurrency(currency);
        BigDecimal multiplier = BigDecimal.valueOf(centMultiplier.get(currency.getDefaultFractionDigits()));
        long cents = amount
            .multiply(multiplier)
            .longValue();
        return new Money(cents, currency);
    }

    private static void checkCurrency(Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency value cannot be null");
        }
    }

    public BigDecimal amount() {
        return BigDecimal.valueOf(cents, currency.getDefaultFractionDigits());
    }

    @Override
    public String toString() {
        return String.format("%s %s", amount(), currency.getCurrencyCode());
    }
}
