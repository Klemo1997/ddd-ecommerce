package com.klemo.ecommerce.sales.cart.service.domain.entity;

import com.klemo.ecommerce.domain.entity.Entity;
import com.klemo.ecommerce.domain.value_object.CartItemId;
import com.klemo.ecommerce.domain.value_object.Money;
import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.domain.value_object.Quantity;

public class CartItem extends Entity<CartItemId> {
    private final ProductId productId;

    private final String title;

    private final Money unitPrice;

    private final Quantity quantity;

    public CartItem(CartItemId id, ProductId productId, String title, Money unitPrice, Quantity quantity) {
        this.setId(id);
        this.productId = productId;
        this.title = title;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}
