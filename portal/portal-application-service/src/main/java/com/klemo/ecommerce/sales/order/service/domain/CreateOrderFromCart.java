package com.klemo.ecommerce.sales.order.service.domain;

import com.klemo.ecommerce.domain.value_object.OrderId;
import com.klemo.ecommerce.domain.value_object.OrderItemId;
import com.klemo.ecommerce.sales.cart.service.domain.CartNotFoundException;
import com.klemo.ecommerce.sales.cart.service.domain.entity.Cart;
import com.klemo.ecommerce.sales.cart.service.domain.entity.CartItem;
import com.klemo.ecommerce.sales.cart.service.domain.port.output.CartRepository;
import com.klemo.ecommerce.sales.order.service.domain.dto.CreateOrderFromCartCommand;
import com.klemo.ecommerce.sales.order.service.domain.dto.CreateOrderFromCartResponse;
import com.klemo.ecommerce.sales.order.service.domain.entity.Order;
import com.klemo.ecommerce.sales.order.service.domain.entity.OrderItem;
import com.klemo.ecommerce.sales.order.service.domain.port.output.OrderRepository;
import com.klemo.ecommerce.sales.order.service.domain.value_object.TrackingId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class CreateOrderFromCart {
    @NonNull private final CartRepository cartRepository;
    @NonNull private final OrderRepository orderRepository;

    public CreateOrderFromCartResponse create(CreateOrderFromCartCommand command) {
        Cart cart = cartRepository.findCartById(command.cartId()).orElseThrow(() -> {
            log.warn("Could not find cart with id: {}", command.cartId());
            return new CartNotFoundException("Could not find cart with id: " + command.cartId());
        });

        if (!cart.hasItems()) {
            throw new Cart.CartHasNoItemsException();
        }

        Order order = orderRepository.save(orderFromCart(cart));
        return new CreateOrderFromCartResponse(order.getTrackingId().getValue(), order.getOrderStatus());
    }

    private Order orderFromCart(Cart cart) {
        return new Order(
                new OrderId(UUID.randomUUID()),
                null,
                cart.getItems().stream().map(this::orderItemFromCartItem).toList(),
                new TrackingId(UUID.randomUUID())
        );
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
