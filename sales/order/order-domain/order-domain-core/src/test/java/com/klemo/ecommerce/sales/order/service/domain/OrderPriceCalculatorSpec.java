package com.klemo.ecommerce.sales.order.service.domain;

import com.klemo.ecommerce.domain.value_object.*;
import com.klemo.ecommerce.sales.order.service.domain.entity.Order;
import com.klemo.ecommerce.sales.order.service.domain.entity.OrderItem;
import com.klemo.ecommerce.sales.order.service.domain.value_object.OrderStatus;
import com.klemo.ecommerce.sales.order.service.domain.value_object.StreetAddress;
import com.klemo.ecommerce.sales.order.service.domain.value_object.TrackingId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

final class OrderPriceCalculatorSpec {
    private static final Currency EUR = Currency.getInstance("EUR");
    public static final OrderId ORDER_ID = new OrderId(UUID.fromString("9de7f703-a34a-4e77-8a65-e87d8417217e"));
    public static final StreetAddress DELIVERY_ADDRESS = new StreetAddress(UUID.fromString("9de7f703-a34a-4e77-8a65-e87d8417217e"), "", "", "");
    public static final TrackingId TRACKING_ID = new TrackingId(UUID.fromString("9de7f703-a34a-4e77-8a65-e87d8417217e"));
    public static final OrderStatus ORDER_STATUS = OrderStatus.PENDING;

    @Test public void testOrderWithSingleItemAndZeroQuantity() {
        var items = List.of(
                getItem(Quantity.ZERO, Money.getInstance(1L, EUR))
        );
        Money expected = Money.getInstance(0, EUR);

        Assertions.assertEquals(expected, new OrderPriceCalculator().getTotal(orderWith(items)));
    }

    @Test public void testOrderWithSingleItemAndOneQuantity() {
        var items = List.of(
                getItem(new Quantity(1), Money.getInstance(1L, EUR))
        );

        Money expected = Money.getInstance(1L, EUR);

        Assertions.assertEquals(expected, new OrderPriceCalculator().getTotal(orderWith(items)));
    }

    @Test public void testOrderWithSingleItemAndLargeQuantity() {
        var items = List.of(
                getItem(new Quantity(2000), Money.getInstance(1L, EUR))
        );

        Money expected = Money.getInstance(2000L, EUR);

        Assertions.assertEquals(expected, new OrderPriceCalculator().getTotal(orderWith(items)));
    }

    @Test public void testOrderWithMultipleItemsAndNonZeroQuantities() {
        var items = List.of(
                getItem(new Quantity(5), Money.getInstance(new BigDecimal("0.50"), EUR)),
                getItem(new Quantity(3), Money.getInstance(new BigDecimal("2.25"), EUR))
        );

        Money expected = Money.getInstance(new BigDecimal("9.25"), EUR);

        Assertions.assertEquals(expected, new OrderPriceCalculator().getTotal(orderWith(items)));
    }

    private static Order orderWith(List<OrderItem> items) {
        return new Order(
                ORDER_ID,
                DELIVERY_ADDRESS,
                items,
                TRACKING_ID,
                ORDER_STATUS
        );
    }

    private static OrderItem getItem(Quantity quantity, Money money) {
        return new OrderItem(
                new OrderItemId(UUID.fromString("9de7f703-a34a-4e77-8a65-e87d8417217e")),
                new ProductId(UUID.fromString("9de7f703-a34a-4e77-8a65-e87d8417217e")),
                quantity,
                money);
    }
}