package com.app.shoppingcartbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems;

    public void addItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        cartItem.setCart(this);
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        this.totalAmount = (BigDecimal) cartItems.stream().map(item -> {
           BigDecimal unitPrice = item.getUnitPrice();
           if(unitPrice == null) {
               return BigDecimal.ZERO;
           }
            return null;
        });
    }

    public void removeItem(CartItem itemToRemove) {
        this.cartItems.remove(itemToRemove);
        itemToRemove.setCart(null);
        updateTotalAmount();
    }
}
