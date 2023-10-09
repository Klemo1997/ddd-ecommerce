package com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess.adapter;

import com.klemo.ecommerce.domain.value_object.*;
import com.klemo.ecommerce.sales.cart.service.domain.entity.Cart;
import com.klemo.ecommerce.sales.cart.service.domain.entity.CartItem;
import com.klemo.ecommerce.sales.cart.service.domain.output.CartRepository;
import com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess.entity.CartEntity;
import com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess.entity.CartItemEntity;
import com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess.repository.CartJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CartRepositoryImpl implements CartRepository {
    private final CartJpaRepository jpaRepository;

    public CartRepositoryImpl(CartJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Cart> findCartById(CartId cartId) {
        return jpaRepository.findById(cartId.getValue())
                .map(this::cartFromCartEntity);
    }

    private Cart cartFromCartEntity(CartEntity cartEntity) {
        List<CartItem> cartItems = cartEntity.getItems().stream().map(this::cartItemFromCartItemEntity).toList();

        return new Cart(
            new CartId(cartEntity.getId()),
            cartItems
        );
    }

    private CartItem cartItemFromCartItemEntity(CartItemEntity cartItemEntity) {
        return new CartItem(
                new CartItemId(cartItemEntity.getId()),
                new ProductId(cartItemEntity.getProductId()),
                cartItemEntity.getTitle(),
                Money.getInstance(cartItemEntity.getUnitPrice(), cartItemEntity.getCurrency()),
                new Quantity(cartItemEntity.getQuantity())
        );
    }
}
