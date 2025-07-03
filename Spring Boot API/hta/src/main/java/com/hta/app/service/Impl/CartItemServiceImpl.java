package com.hta.app.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hta.app.model.CartItem;
import com.hta.app.model.CartItemId;
import com.hta.app.repository.CartItemRepository;
import com.hta.app.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Iterable<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public Optional<CartItem> findById(CartItemId id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public CartItem save(CartItem item) {
        if (item.getId() == null) {
            Long productId = item.getProduct() != null ? item.getProduct().getId() : null;
            Long cartId = item.getShoppingCart() != null ? item.getShoppingCart().getId() : null;

            if (productId == null || cartId == null) {
                throw new IllegalArgumentException("Faltan los IDs de producto o carrito");
            }

            CartItemId id = new CartItemId(productId, cartId);
            item.setId(id);
        }

        return cartItemRepository.save(item);
    }

    @Override
    public void delete(CartItemId id) {
        cartItemRepository.deleteById(id);
    }
}
