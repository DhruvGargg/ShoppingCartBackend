package com.app.shoppingcartbackend.service.cart;

import com.app.shoppingcartbackend.model.Cart;
import com.app.shoppingcartbackend.model.CartItem;
import com.app.shoppingcartbackend.model.Product;
import com.app.shoppingcartbackend.repository.cart.CartRepository;
import com.app.shoppingcartbackend.repository.cartitem.CartItemRepository;
import com.app.shoppingcartbackend.service.product.ProductService;
import com.app.shoppingcartbackend.service.product.ProductServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class CartItemService implements CartItemServiceInterface {

    private final CartItemRepository cartItemRepository;
    private final ProductServiceInterface productService;
    private final CartServiceInterface cartService;
    private final CartRepository cartRepository;

    @Override
    public void addItemToCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst().orElse(new CartItem());
        if(cartItem.getId() == null) {
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cartItem.setUnitPrice(product.getPrice());
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {

    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, Integer quantity) {

    }
}
