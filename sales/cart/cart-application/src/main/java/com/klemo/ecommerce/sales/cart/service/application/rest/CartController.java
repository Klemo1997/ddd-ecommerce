package com.klemo.ecommerce.sales.cart.service.application.rest;

import com.klemo.ecommerce.domain.value_object.CartId;
import com.klemo.ecommerce.sales.cart.service.domain.CartNotFoundException;
import com.klemo.ecommerce.sales.cart.service.domain.ListCartItems;
import com.klemo.ecommerce.sales.cart.service.domain.dto.ListCartItemsQuery;
import com.klemo.ecommerce.sales.cart.service.domain.dto.ListCartItemsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/v1/carts", produces = "application/json")
public class CartController {
    private final ListCartItems listCartItems;

    public CartController(ListCartItems listCartItems) {
        this.listCartItems = listCartItems;
    }

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
}
