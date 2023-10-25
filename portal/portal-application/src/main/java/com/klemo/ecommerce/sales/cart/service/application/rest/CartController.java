package com.klemo.ecommerce.sales.cart.service.application.rest;

import com.klemo.ecommerce.application.rest.dto.ErrorResponse;
import com.klemo.ecommerce.domain.value_object.CartId;
import com.klemo.ecommerce.sales.cart.service.domain.AddProductToCart;
import com.klemo.ecommerce.sales.cart.service.domain.CartNotFoundException;
import com.klemo.ecommerce.sales.cart.service.domain.ListCartItems;
import com.klemo.ecommerce.sales.cart.service.domain.dto.AddProductToCartCommand;
import com.klemo.ecommerce.sales.cart.service.domain.dto.AddProductToCartResponse;
import com.klemo.ecommerce.sales.cart.service.domain.dto.ListCartItemsQuery;
import com.klemo.ecommerce.sales.cart.service.domain.dto.ListCartItemsResponse;
import com.klemo.ecommerce.sales.cart.service.domain.entity.Cart;
import com.klemo.ecommerce.sales.catalog.service.domain.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/carts", produces = "application/json")
public class CartController {
    private final ListCartItems listCartItems;
    private final AddProductToCart addProductToCart;

    @GetMapping("/{cartId}")
    public ResponseEntity<ListCartItemsResponse> listItems(@PathVariable UUID cartId) {
        try {
            ListCartItemsQuery query = new ListCartItemsQuery(new CartId(cartId));
            ListCartItemsResponse response = listCartItems.listCart(query);
            return ResponseEntity.ok(response);
        } catch (CartNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{cartId}/item")
    public ResponseEntity<Object> addItem(@PathVariable UUID cartId, @RequestBody AddProductToCartCommand command) {
        log.info("Adding item to cart: {} , details: {}", cartId, command);
        try {
            AddProductToCartResponse response = addProductToCart.add(cartId, command);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (CartNotFoundException exception) {
            log.warn("Could not find cart with id: {}", cartId);
            return ResponseEntity.unprocessableEntity().body(ErrorResponse.of("Could not find cart with id: "
                    + cartId));
        } catch (ProductNotFoundException exception) {
            log.warn("Could not find product, data: {}", command);
            return ResponseEntity.unprocessableEntity().body(ErrorResponse.of("Could not find product with id: "
                    + command.productId()));
        } catch (Cart.CartAlreadyContainsProductException exception) {
            log.warn("Cart already contains product, data: {}", command);
            return ResponseEntity.unprocessableEntity().body(ErrorResponse.of("Cart already contains this product"));
        }
    }
}
