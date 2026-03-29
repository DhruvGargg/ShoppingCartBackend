package com.app.shoppingcartbackend.exception.ResourceNotFound;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
