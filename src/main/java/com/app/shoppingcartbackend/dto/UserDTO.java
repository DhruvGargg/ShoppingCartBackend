package com.app.shoppingcartbackend.dto;

import com.app.shoppingcartbackend.model.Cart;
import com.app.shoppingcartbackend.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private List<OrderDTO> orders;
    private CartDTO cart;
}
