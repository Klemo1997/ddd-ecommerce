package com.klemo.ecommerce.sales.order.service.application.rest;

import com.klemo.ecommerce.application.rest.dto.ErrorResponse;
import com.klemo.ecommerce.sales.cart.service.domain.CartNotFoundException;
import com.klemo.ecommerce.sales.cart.service.domain.entity.Cart;
import com.klemo.ecommerce.sales.order.service.domain.CreateOrderFromCart;
import com.klemo.ecommerce.sales.order.service.domain.dto.CreateOrderFromCartCommand;
import com.klemo.ecommerce.sales.order.service.domain.dto.CreateOrderFromCartResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/orders", produces = "application/json")
@RequiredArgsConstructor
public class OrderController {
    private final CreateOrderFromCart createOrderFromCart;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody CreateOrderFromCartCommand command) {
        log.info("Creating order from cart: {}", command.cartId());

        try {
            CreateOrderFromCartResponse response = createOrderFromCart.create(command);

            log.info("Order created with tracking id: {}", response.trackingId());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (CartNotFoundException e) {
            log.warn("Could not find cart with id: {}", command.cartId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.of("Could not find cart with id: " + command.cartId()));
        } catch (Cart.CartHasNoItemsException e) {
            log.warn("Cart with id: {} has no items, so order cannot be created from it", command.cartId());
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorResponse.of(String.format(
                            "Cart with id: %s has no items, so order cannot be created from it", command.cartId())));
        }
    }
}
