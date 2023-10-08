package com.klemo.ecommerce.sales.cart.service.domain.entity;

import com.klemo.ecommerce.domain.entity.AggregateRoot;
import com.klemo.ecommerce.domain.value_object.CartId;

import java.util.List;

public final class Cart extends AggregateRoot<CartId> {
    private final List<CartItem> items;

    public Cart(CartId id, List<CartItem> items) {
        this.setId(id);
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }
}
