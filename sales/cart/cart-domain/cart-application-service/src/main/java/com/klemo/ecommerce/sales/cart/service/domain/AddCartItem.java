package com.klemo.ecommerce.sales.cart.service.domain;

import com.klemo.ecommerce.domain.value_object.CartId;
import com.klemo.ecommerce.domain.value_object.CartItemId;
import com.klemo.ecommerce.domain.value_object.ProductId;
import com.klemo.ecommerce.domain.value_object.Quantity;
import com.klemo.ecommerce.sales.cart.service.domain.dto.AddCartItemCommand;
import com.klemo.ecommerce.sales.cart.service.domain.dto.AddCartItemResponse;
import com.klemo.ecommerce.sales.cart.service.domain.entity.Cart;
import com.klemo.ecommerce.sales.cart.service.domain.entity.CartItem;
import com.klemo.ecommerce.sales.cart.service.domain.port.output.CartRepository;
import com.klemo.ecommerce.sales.domain.ProductNotFoundException;
import com.klemo.ecommerce.sales.domain.entity.Product;
import com.klemo.ecommerce.sales.domain.port.output.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class AddCartItem {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public AddCartItemResponse add(UUID cartId, AddCartItemCommand command) {
        Cart cart = cartRepository.findCartById(new CartId(cartId))
                .orElseThrow(() -> {
                    log.warn("Could not find cart with id: {}", cartId);
                    return new CartNotFoundException("Could not find cart with id: " + cartId);
                });

        Product product = productRepository.findProductById(new ProductId(command.productId()))
                .orElseThrow(() -> {
                    log.warn("Could not find product with id: {}", command.productId());
                    return new ProductNotFoundException("Could not find product with id: " + command.productId());
                });

        cart.addItem(itemFromProductAndQuantity(product, new Quantity(command.quantity())));

        Cart mutatedCart = cartRepository.save(cart);

        return new AddCartItemResponse(mutatedCart.getId().getValue(), mapItemsToItemsDto(mutatedCart.getItems()));
    }

    private List<com.klemo.ecommerce.sales.cart.service.domain.dto.CartItem> mapItemsToItemsDto(List<CartItem> items) {
        return items.stream().map(item -> new com.klemo.ecommerce.sales.cart.service.domain.dto.CartItem(
            item.getProductId().getValue(),
            item.getTitle(),
            item.getUnitPrice().amount(),
            item.getUnitPrice().currency().getCurrencyCode(),
            item.getQuantity().value()
        )).toList();
    }

    private CartItem itemFromProductAndQuantity(Product product, Quantity quantity) {
        return new CartItem(
                new CartItemId(UUID.randomUUID()),
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                quantity
        );
    }
}
