package com.klemo.ecommerce.sales.cart.service.domain;

import com.klemo.ecommerce.sales.cart.service.domain.dto.ListCartItemsQuery;
import com.klemo.ecommerce.sales.cart.service.domain.dto.ListCartResponse;
import com.klemo.ecommerce.sales.cart.service.domain.entity.Cart;
import com.klemo.ecommerce.sales.cart.service.domain.output.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ListCartItems {
    private final CartRepository cartRepository;

    public ListCartItems(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional(readOnly = true)
    ListCartResponse listCart(ListCartItemsQuery query) {
        Cart cart = cartRepository.findCartById(query.cartId())
                .orElseThrow(() -> {
                    log.warn("Could not find cart with id: {}", query.cartId());
                    return new CartNotFoundException("Could not find cart with id: " + query.cartId());
                });
        return new ListCartResponse(cart.getItems());
    }
}
