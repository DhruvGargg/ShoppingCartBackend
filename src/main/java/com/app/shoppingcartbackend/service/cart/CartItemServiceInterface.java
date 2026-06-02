package com.app.shoppingcartbackend.service.cart;

import com.app.shoppingcartbackend.model.CartItem;

public interface CartItemServiceInterface {
    void addItemToCart(Long cartId, Long productId, Integer quantity);
    void removeItemFromCart(Long cartId, Long itemId);
    void updateItemQuantity(Long cartId, Long itemId, Integer quantity);

    CartItem getCartItem(Long cartId, Long itemId);
}
