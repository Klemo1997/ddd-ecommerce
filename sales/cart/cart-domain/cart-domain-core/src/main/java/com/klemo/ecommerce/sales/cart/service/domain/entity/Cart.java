package com.klemo.ecommerce.sales.cart.service.domain.entity;

import com.klemo.ecommerce.domain.entity.AggregateRoot;
import com.klemo.ecommerce.domain.value_object.CartId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cart extends AggregateRoot<CartId> {
    private List<CartItem> items;

    public Cart(CartId id, List<CartItem> items) {
        this.setId(id);
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        if (cartContainsProduct(item)) {
            throw new CartAlreadyContainsProductException();
        }
        List<CartItem> newItems = new ArrayList<>(items);
        newItems.add(item);
        this.items = Collections.unmodifiableList(newItems);
    }

    private boolean cartContainsProduct(CartItem item) {
        return items.stream().anyMatch(i -> i.getProductId().equals(item.getProductId()));
    }

    public static class CartAlreadyContainsProductException extends RuntimeException {}
}
