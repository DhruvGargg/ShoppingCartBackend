package com.app.shoppingcartbackend.controller;

import com.app.shoppingcartbackend.exception.ResourceNotFound.ResourceNotFoundException;
import com.app.shoppingcartbackend.model.Cart;
import com.app.shoppingcartbackend.response.APIResponse;
import com.app.shoppingcartbackend.service.cart.CartServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cart")
public class CartController {

    private final CartServiceInterface cartService;

    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<APIResponse> getCart(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCart(cartId);
            return ResponseEntity.ok(new APIResponse("Success", cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<APIResponse> clearCart(@PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new APIResponse("Cart Cleared Successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/total-price/{cartId}")
    public ResponseEntity<APIResponse> getTotalAmount(@PathVariable Long cartId) {
        try {
            cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new APIResponse("Success", cartService.getTotalPrice(cartId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }
}
