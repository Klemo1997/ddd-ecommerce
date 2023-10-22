package com.klemo.ecommerce.sales.infrastructure.dataaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "products")
@Entity
public class ProductEntity {
    @Id private UUID id;
    
    private String title;

    private String description;

    private BigDecimal unitPrice;
    
    private String currency;

    public void setCurrency(Currency currency) {
        this.currency = currency.getCurrencyCode();
    }

    public Currency getCurrency() {
        return Currency.getInstance(currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
