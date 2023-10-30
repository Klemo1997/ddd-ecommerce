package com.klemo.ecommerce.warehouse.service.domain.entity;

import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.domain.value_object.Quantity;
import com.klemo.ecommerce.warehouse.service.domain.entity.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

public class StockSpec {
    public static final ProductId PRODUCT_ID = new ProductId(
            UUID.fromString("9de7f703-a34a-4e77-8a65-e87d8417217e"));
    public static final ProductId ANOTHER_PRODUCT_ID = new ProductId(
            UUID.fromString("645dcef3-8ac1-42ba-9523-258ce7c385de"));

    public static Stream<Arguments> provideForIsEmpty() {
        return Stream.of(
            Arguments.of(new Stock(PRODUCT_ID, new Quantity(0L)), true),
            Arguments.of(new Stock(PRODUCT_ID, new Quantity(1L)), false),
            Arguments.of(new Stock(PRODUCT_ID, new Quantity(5L)), false)
        );
    }

    @Test void testEquality() {
        Stock stock = new Stock(PRODUCT_ID, Quantity.ZERO);
        Stock anotherStock = new Stock(PRODUCT_ID, new Quantity(5L));

        Assertions.assertEquals(stock, anotherStock);
    }

    @Test void testInequality() {
        Stock stock = new Stock(PRODUCT_ID);
        Stock anotherStock = new Stock(ANOTHER_PRODUCT_ID);

        Assertions.assertNotEquals(stock, anotherStock);
    }

    @ParameterizedTest
    @MethodSource("provideForIsEmpty")
    void testIsEmpty(Stock stock, boolean expected) {
        Assertions.assertEquals(expected, stock.isEmpty());
    }

    @Test void testAdd() {
        Stock stock = new Stock(PRODUCT_ID, new Quantity(5L));
        stock.add(new Quantity(1L));
        Assertions.assertEquals(new Quantity(6L), stock.getQuantity());
    }

    @Test void testTake() {
        Stock stock = new Stock(PRODUCT_ID, new Quantity(5L));
        stock.take(new Quantity(3L));
        Assertions.assertEquals(new Quantity(2L), stock.getQuantity());
    }

    @Test void testTakeWhenThereIsNotEnoughInStock() {
        Assertions.assertThrows(Stock.NotEnoughInStockException.class, () ->
                new Stock(PRODUCT_ID, new Quantity(0L))
                    .take(new Quantity(1L)));
    }
}
