package com.app.shoppingcartbackend.exception.CategoryNotFound;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
