package com.klemo.ecommerce.warehouse.service.infrastructure.dataaccess.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks")
@Entity
public class StockEntity {
    @Id
    @Column(name = "PRODUCT_ID")
    private UUID productId;

    @NotNull private Long quantity;
}