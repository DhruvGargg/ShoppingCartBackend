package com.app.shoppingcartbackend.service.cart;


import com.app.shoppingcartbackend.model.Cart;
import com.app.shoppingcartbackend.repository.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements CartServiceInterface {

    private final CartRepository cartRepository;

    @Override
    public Cart getCart(Long id) {
        return null;
    }

    @Override
    public void clearCart(Long id) {

    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        return null;
    }
}
