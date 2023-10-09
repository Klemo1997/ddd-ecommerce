package com.klemo.ecommerce.domain.value_object;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

public class MoneySpec {
    public static final Currency EUR = Currency.getInstance("EUR");
    public static final Currency USD = Currency.getInstance("USD");
    public static final Currency JPY = Currency.getInstance("JPY");

    @Test void shouldBeEqual() {
        assertEquals(new Money(50L, EUR), new Money(50L, EUR));
    }

    @Test void shouldNotBeEqualWhenCentAreDifferent() {
        assertNotEquals(new Money(100L, EUR), new Money(50L, EUR));
    }

    @Test void shouldNotBeEqualWhenCurrenciesAreDifferent() {
        assertNotEquals(new Money(50L, EUR), new Money(50L, USD));
    }

    @Test void shouldNotBeCreatedWithNullCurrency() {
        assertThrows(IllegalArgumentException.class, () -> new Money(50L, null));
    }

    @Test void shouldNotGetInstanceWithNullCurrencyAndLongAmount() {
        assertThrows(IllegalArgumentException.class, () -> Money.getInstance(50L, null));
    }

    @Test void shouldNotGetInstanceWithNullCurrencyAndBigDecimalAmount() {
        assertThrows(IllegalArgumentException.class, () -> Money.getInstance(BigDecimal.ONE, null));
    }

    @Test void shouldGetInstanceWithLongEURAmountProvided() {
        final long cents = 500L;
        final long amount = 5;
        assertEquals(Money.getInstance(amount, EUR), new Money(cents, EUR));
    }

    @Test void shouldGetInstanceWithLongJPYAmountProvided() {
        final long cents = 500L;
        final long amount = 500L;
        assertEquals(Money.getInstance(amount, JPY), new Money(cents, JPY));
    }

    @Test void shouldGetInstanceWithDecimalJPYAmountProvided() {
        final long cents = 1L;
        final BigDecimal amount = new BigDecimal("1.50");
        assertEquals(Money.getInstance(amount, JPY), new Money(cents, JPY));
    }

    @Test void shouldGetCorrectAmount() {
        final BigDecimal amount = new BigDecimal("1.50");
        assertEquals(amount, Money.getInstance(amount, EUR).amount());
    }

    @Test void shouldConvertToString() {
        assertEquals("5.00 EUR", Money.getInstance(5L, EUR).toString());
    }
}
