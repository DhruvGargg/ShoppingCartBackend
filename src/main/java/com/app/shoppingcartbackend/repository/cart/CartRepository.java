package com.app.shoppingcartbackend.repository.cart;

import com.app.shoppingcartbackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
