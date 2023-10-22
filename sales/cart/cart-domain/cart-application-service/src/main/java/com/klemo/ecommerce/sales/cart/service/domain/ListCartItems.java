package com.klemo.ecommerce.sales.cart.service.domain;

import com.klemo.ecommerce.sales.cart.service.domain.dto.ListCartItemsQuery;
import com.klemo.ecommerce.sales.cart.service.domain.dto.ListCartItemsResponse;
import com.klemo.ecommerce.sales.cart.service.domain.entity.Cart;
import com.klemo.ecommerce.sales.cart.service.domain.entity.CartItem;
import com.klemo.ecommerce.sales.cart.service.domain.port.output.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class ListCartItems {
    private final CartRepository cartRepository;

    public ListCartItems(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional(readOnly = true)
    public ListCartItemsResponse listCart(ListCartItemsQuery query) {
        Cart cart = cartRepository.findCartById(query.cartId())
                .orElseThrow(() -> {
                    log.warn("Could not find cart with id: {}", query.cartId());
                    return new CartNotFoundException("Could not find cart with id: " + query.cartId());
                });
        return new ListCartItemsResponse(cartItemsToCartItemDtos(cart.getItems()));
    }

    private List<com.klemo.ecommerce.sales.cart.service.domain.dto.CartItem> cartItemsToCartItemDtos(
            List<CartItem> items) {
        return items.stream().map(this::cartItemToCartItemDto).toList();
    }

    private com.klemo.ecommerce.sales.cart.service.domain.dto.CartItem cartItemToCartItemDto(CartItem cartItem) {
        return new com.klemo.ecommerce.sales.cart.service.domain.dto.CartItem(
            cartItem.getProductId().getValue(),
            cartItem.getTitle(),
            cartItem.getUnitPrice().amount(),
            cartItem.getUnitPrice().currency().getCurrencyCode(),
            cartItem.getQuantity().value()
        );
    }
}
