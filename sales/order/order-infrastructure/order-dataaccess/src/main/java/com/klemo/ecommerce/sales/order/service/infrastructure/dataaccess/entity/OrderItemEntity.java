package com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
@Entity
public class OrderItemEntity {
    @Id private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    private UUID productID;

    private Long quantity;

    private BigDecimal price;

    private String currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntity that = (OrderItemEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Currency getCurrency() {
        return Currency.getInstance(currency);
    }

    public void setCurrency(Currency currency) {
        this.currency = currency.getCurrencyCode();
    }
}
