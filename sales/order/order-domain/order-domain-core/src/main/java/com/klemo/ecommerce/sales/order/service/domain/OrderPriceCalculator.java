package com.klemo.ecommerce.sales.order.service.domain;

import com.klemo.ecommerce.domain.value_object.Money;
import com.klemo.ecommerce.sales.order.service.domain.entity.Order;
import com.klemo.ecommerce.sales.order.service.domain.entity.OrderItem;

import java.util.Currency;

public class OrderPriceCalculator {
    public Money getTotal(Order order) {
        Currency currency = order.getItems().get(0).getPrice().currency();
        return order.getItems().stream().reduce(
            Money.getInstance(0, currency),
            (Money acc, OrderItem item) -> acc.add(item.getPrice().multiply(item.getQuantity().value())),
            Money::add
        );
    }
}
