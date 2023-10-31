package com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess.adapter;

import com.klemo.ecommerce.domain.value_object.*;
import com.klemo.ecommerce.sales.cart.service.domain.entity.Cart;
import com.klemo.ecommerce.sales.cart.service.domain.entity.CartItem;
import com.klemo.ecommerce.sales.cart.service.domain.port.output.CartRepository;
import com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess.entity.CartEntity;
import com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess.entity.CartItemEntity;
import com.klemo.ecommerce.sales.cart.service.infrastructure.dataaccess.repository.CartJpaRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    @NonNull final CartJpaRepository jpaRepository;

    @Override
    public Optional<Cart> findCartById(@NonNull CartId cartId) {
        return jpaRepository.findById(cartId.getValue())
                .map(this::cartFromCartEntity);
    }

    @Override
    public Cart save(@NonNull Cart cart) {
        return cartFromCartEntity(jpaRepository.save(cartEntityFromCart(cart)));
    }

    private CartEntity cartEntityFromCart(Cart cart) {
        CartEntity cartEntity = new CartEntity(
            cart.getId().getValue(),
            cart.getItems().stream().map(this::cartItemEntityFromCartItem).toList()
        );

        cartEntity.getItems().forEach(item -> item.setCart(cartEntity));

        return cartEntity;
    }

    private CartItemEntity cartItemEntityFromCartItem(@NonNull CartItem cartItem) {
        return new CartItemEntity(
            cartItem.getId().getValue(),
            null,
            cartItem.getProductId().getValue(),
            cartItem.getTitle(),
            cartItem.getUnitPrice().amount(),
            cartItem.getUnitPrice().currency().getCurrencyCode(),
            cartItem.getQuantity().value()
        );
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
