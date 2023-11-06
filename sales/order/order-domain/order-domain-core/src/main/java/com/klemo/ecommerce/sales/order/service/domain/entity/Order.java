package com.klemo.ecommerce.sales.order.service.domain.entity;

import com.klemo.ecommerce.domain.entity.AggregateRoot;
import com.klemo.ecommerce.domain.value_object.OrderId;
import com.klemo.ecommerce.sales.order.service.domain.OrderPriceCalculator;
import com.klemo.ecommerce.sales.order.service.domain.value_object.OrderStatus;
import com.klemo.ecommerce.sales.order.service.domain.value_object.StreetAddress;
import com.klemo.ecommerce.sales.order.service.domain.value_object.TrackingId;

import java.util.Currency;
import java.util.List;

public class Order extends AggregateRoot<OrderId> {
    private StreetAddress deliveryAddress;
    private List<OrderItem> items;
    private TrackingId trackingId;
    private OrderStatus orderStatus;

    public Order(
            OrderId id,
            StreetAddress deliveryAddress,
            List<OrderItem> items,
            TrackingId trackingId,
            OrderStatus orderStatus
    ) {
        this.setId(id);
        this.deliveryAddress = deliveryAddress;
        this.items = items;
        this.trackingId = trackingId;
        this.orderStatus = orderStatus;

        checkOrderIsNotEmpty();
        checkItemCurrencies();
    }

    private void checkOrderIsNotEmpty() {
        if (items.isEmpty()) {
            throw new OrderHasNoItemsException();
        }
    }

    private void checkItemCurrencies() {
        if (items.isEmpty()) {
            return;
        }

        Currency currency = items.get(0).getPrice().currency();

        for (OrderItem item : items) {
            if (!item.getPrice().currency().equals(currency)) {
                throw new OrderHasItemsWithDifferentCurrenciesException();
            }
        }
    }

    public Order(
            OrderId id,
            StreetAddress deliveryAddress,
            List<OrderItem> items,
            TrackingId trackingId
    ) {
        this.setId(id);
        this.deliveryAddress = deliveryAddress;
        this.items = items;
        this.trackingId = trackingId;
        this.orderStatus = OrderStatus.PENDING;
    }


    public StreetAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public static class OrderHasNoItemsException extends RuntimeException {}
    public static class OrderHasItemsWithDifferentCurrenciesException extends RuntimeException {}
}
