package com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess.entity;

import com.klemo.ecommerce.sales.order.service.domain.value_object.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders", indexes = {
    @Index(name = "i_tracking_id", columnList = "trackingId")
})
@Entity
public class OrderEntity {
    @Id private UUID id;
    private UUID trackingId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderAddressEntity address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(trackingId, that.trackingId) && orderStatus == that.orderStatus && Objects.equals(address, that.address) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
