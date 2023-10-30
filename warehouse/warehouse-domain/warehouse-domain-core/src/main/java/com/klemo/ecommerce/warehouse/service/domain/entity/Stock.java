package com.klemo.ecommerce.warehouse.service.domain.entity;

import com.klemo.ecommerce.domain.entity.AggregateRoot;
import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.domain.value_object.Quantity;

public class Stock extends AggregateRoot<ProductId> {
    private Quantity quantity;

    public Stock(ProductId productId) {
        setId(productId);
        quantity = Quantity.ZERO;
    }

    public Stock(ProductId productId, Quantity quantity) {
        setId(productId);
        this.quantity = quantity;
    }

    public boolean isEmpty() {
        return quantity.isEmpty();
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void add(Quantity quantity) {
        this.quantity = this.quantity.add(quantity);
    }

    public void take(Quantity quantity) {
        try {
            this.quantity = this.quantity.subtract(quantity);
        } catch (IllegalArgumentException e) {
            throw new NotEnoughInStockException(e);
        }
    }

    public static class NotEnoughInStockException extends RuntimeException {
        public NotEnoughInStockException(Throwable cause) {
            super(cause);
        }
    }
}
