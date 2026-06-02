package com.app.shoppingcartbackend.repository.cart;

import com.app.shoppingcartbackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
    

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @org.springframework.data.jpa.repository.Query("SELECT c FROM Cart c WHERE c.user.userId = :userId")
    Cart findByUserId(@org.springframework.data.repository.query.Param("userId") Long userId);
}
