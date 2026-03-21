package com.app.shoppingcartbackend.exception.ProductNotFound;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
