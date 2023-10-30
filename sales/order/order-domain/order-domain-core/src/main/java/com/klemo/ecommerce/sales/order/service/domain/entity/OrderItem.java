package com.klemo.ecommerce.sales.order.service.domain.entity;

import com.klemo.ecommerce.domain.entity.Entity;
import com.klemo.ecommerce.domain.value_object.Money;
import com.klemo.ecommerce.domain.value_object.OrderItemId;
import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.domain.value_object.Quantity;

public class OrderItem extends Entity<OrderItemId> {
    private final ProductId productId;
    private final Quantity quantity;
    private final Money price;

    public OrderItem(OrderItemId id, ProductId productId, Quantity quantity, Money price) {
        setId(id);
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductId getProductId() {
        return productId;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }
}
