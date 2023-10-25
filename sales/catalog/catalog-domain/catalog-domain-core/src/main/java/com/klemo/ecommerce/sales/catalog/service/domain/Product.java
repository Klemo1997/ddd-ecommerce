package com.klemo.ecommerce.sales.catalog.service.domain;

import com.klemo.ecommerce.domain.entity.Entity;
import com.klemo.ecommerce.domain.value_object.Money;
import com.klemo.ecommerce.domain.value_object.ProductId;

public final class Product extends Entity<ProductId> {

    private String title;

    private String description;

    private Money price;

    public Product(ProductId id) {
        this.setId(id);
    }

    public Product(ProductId id, String title, String description, Money price) {
        this.setId(id);
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changePrice(Money price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Money getPrice() {
        return price;
    }
}
