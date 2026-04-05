package com.app.shoppingcartbackend.repository.cartitem;

import com.app.shoppingcartbackend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
