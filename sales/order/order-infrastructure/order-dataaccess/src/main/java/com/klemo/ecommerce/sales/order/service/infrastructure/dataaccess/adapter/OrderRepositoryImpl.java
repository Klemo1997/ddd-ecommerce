package com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess.adapter;

import com.klemo.ecommerce.domain.value_object.*;
import com.klemo.ecommerce.sales.order.service.domain.entity.Order;
import com.klemo.ecommerce.sales.order.service.domain.entity.OrderItem;
import com.klemo.ecommerce.sales.order.service.domain.port.output.OrderRepository;
import com.klemo.ecommerce.sales.order.service.domain.value_object.StreetAddress;
import com.klemo.ecommerce.sales.order.service.domain.value_object.TrackingId;
import com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess.entity.OrderAddressEntity;
import com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess.entity.OrderEntity;
import com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess.entity.OrderItemEntity;
import com.klemo.ecommerce.sales.order.service.infrastructure.dataaccess.repository.OrderJpaRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderRepositoryImpl implements OrderRepository {
    @NonNull private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        OrderEntity savedEntity = orderJpaRepository.save(orderEntityFromOrder(order));
        return orderFromOrderEntity(savedEntity);
    }

    private OrderEntity orderEntityFromOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity(
            order.getId().getValue(),
            order.getTrackingId().getValue(),
            order.getOrderStatus(),
            orderAddressEntityFromDeliveryAddress(order.getDeliveryAddress()),
            order.getItems().stream().map(this::orderItemEntityFromOrderItem).toList()
        );

        orderEntity.getItems().forEach(item -> item.setOrder(orderEntity));
        orderEntity.getAddress().setOrder(orderEntity);

        return orderEntity;
    }

    private OrderItemEntity orderItemEntityFromOrderItem(OrderItem orderItem) {
        return new OrderItemEntity(
                orderItem.getId().getValue(),
                null,
                orderItem.getProductId().getValue(),
                orderItem.getQuantity().value(),
                orderItem.getPrice().amount(),
                orderItem.getPrice().currency().getCurrencyCode()
        );
    }

    private OrderAddressEntity orderAddressEntityFromDeliveryAddress(StreetAddress deliveryAddress) {
        return new OrderAddressEntity(
                deliveryAddress.id(),
                null,
                deliveryAddress.street(),
                deliveryAddress.postalCode(),
                deliveryAddress.city()
        );
    }

    private Order orderFromOrderEntity(OrderEntity orderEntity) {
        return new Order(
            new OrderId(orderEntity.getId()),
            deliveryAddressFromOrderAddressEntity(orderEntity.getAddress()),
            orderEntity.getItems().stream().map(this::orderItemFromOrderItemEntity).toList(),
            new TrackingId(orderEntity.getTrackingId()),
            orderEntity.getOrderStatus()
        );
    }

    private OrderItem orderItemFromOrderItemEntity(OrderItemEntity orderItemEntity) {
        return new OrderItem(
                new OrderItemId(orderItemEntity.getId()),
                new ProductId(orderItemEntity.getProductID()),
                new Quantity(orderItemEntity.getQuantity()),
                Money.getInstance(orderItemEntity.getPrice(), orderItemEntity.getCurrency())
        );
    }

    private StreetAddress deliveryAddressFromOrderAddressEntity(OrderAddressEntity address) {
        return new StreetAddress(
                address.getId(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
    }
}
