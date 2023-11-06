package com.klemo.ecommerce.sales.order.service.domain;

import com.klemo.ecommerce.domain.value_object.CartId;
import com.klemo.ecommerce.domain.value_object.OrderId;
import com.klemo.ecommerce.domain.value_object.OrderItemId;
import com.klemo.ecommerce.sales.cart.service.domain.CartNotFoundException;
import com.klemo.ecommerce.sales.cart.service.domain.entity.Cart;
import com.klemo.ecommerce.sales.cart.service.domain.entity.CartItem;
import com.klemo.ecommerce.sales.cart.service.domain.port.output.CartRepository;
import com.klemo.ecommerce.sales.order.service.domain.dto.CreateOrderFromCartCommand;
import com.klemo.ecommerce.sales.order.service.domain.dto.CreateOrderFromCartResponse;
import com.klemo.ecommerce.sales.order.service.domain.dto.OrderAddress;
import com.klemo.ecommerce.sales.order.service.domain.dto.OrderCreatedEvent;
import com.klemo.ecommerce.sales.order.service.domain.entity.Order;
import com.klemo.ecommerce.sales.order.service.domain.entity.OrderItem;
import com.klemo.ecommerce.sales.order.service.domain.port.output.OrderCreatedPaymentRequestEventPublisher;
import com.klemo.ecommerce.sales.order.service.domain.port.output.OrderRepository;
import com.klemo.ecommerce.sales.order.service.domain.value_object.StreetAddress;
import com.klemo.ecommerce.sales.order.service.domain.value_object.TrackingId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class CreateOrderFromCart {
    @NonNull private final CartRepository cartRepository;
    @NonNull private final OrderRepository orderRepository;
    @NonNull private final OrderCreatedPaymentRequestEventPublisher orderCreatedPaymentRequestEventPublisher;

    public CreateOrderFromCartResponse create(@NonNull CreateOrderFromCartCommand command) {
        Cart cart = cartRepository.findCartById(new CartId(command.cartId())).orElseThrow(() -> {
            log.warn("Could not find cart with id: {}", command.cartId());
            return new CartNotFoundException("Could not find cart with id: " + command.cartId());
        });

        if (!cart.hasItems()) {
            throw new Cart.CartHasNoItemsException();
        }

        Order order = orderRepository.save(orderFromCartAndAddress(cart, command.address()));
        orderCreatedPaymentRequestEventPublisher.publish(domainEventFrom(order));

        return new CreateOrderFromCartResponse(order.getTrackingId().getValue(), order.getOrderStatus());
    }

    private OrderCreatedEvent domainEventFrom(Order order) {
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    private Order orderFromCartAndAddress(Cart cart, OrderAddress address) {
        return new Order(
                new OrderId(UUID.randomUUID()),
                deliveryAddressFromOrderAddress(address),
                cart.getItems().stream().map(this::orderItemFromCartItem).toList(),
                new TrackingId(UUID.randomUUID())
        );
    }

    private StreetAddress deliveryAddressFromOrderAddress(OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.street(),
                address.postalCode(),
                address.city());
    }

    private OrderItem orderItemFromCartItem(CartItem cartItem) {
        return new OrderItem(
                new OrderItemId(UUID.randomUUID()),
                cartItem.getProductId(),
                cartItem.getQuantity(),
                cartItem.getUnitPrice()
        );
    }
}
