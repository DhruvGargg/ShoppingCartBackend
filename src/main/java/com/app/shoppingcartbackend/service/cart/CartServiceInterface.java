package com.app.shoppingcartbackend.service.cart;

import com.app.shoppingcartbackend.model.Cart;

import java.math.BigDecimal;

public interface CartServiceInterface {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
}
