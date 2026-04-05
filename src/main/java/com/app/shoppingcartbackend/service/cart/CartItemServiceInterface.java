package com.app.shoppingcartbackend.service.cart;

import com.app.shoppingcartbackend.model.CartItem;

public interface CartItemServiceInterface {
    void addItemToCart(Long cartId, Long productId, Integer quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, Integer quantity);
}
