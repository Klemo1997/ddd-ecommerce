package com.klemo.ecommerce.sales.cart.service.domain.port.output;

import com.klemo.ecommerce.domain.value_object.CartId;
import com.klemo.ecommerce.sales.cart.service.domain.entity.Cart;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findCartById(CartId cartId);

    Cart save(Cart cart);
}
