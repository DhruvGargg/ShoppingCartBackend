package com.app.shoppingcartbackend.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
