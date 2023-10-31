package com.klemo.ecommerce.sales.order.service.domain.entity;

import com.klemo.ecommerce.domain.entity.AggregateRoot;
import com.klemo.ecommerce.domain.value_object.OrderId;
import com.klemo.ecommerce.sales.order.service.domain.value_object.OrderStatus;
import com.klemo.ecommerce.sales.order.service.domain.value_object.StreetAddress;
import com.klemo.ecommerce.sales.order.service.domain.value_object.TrackingId;

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
}
