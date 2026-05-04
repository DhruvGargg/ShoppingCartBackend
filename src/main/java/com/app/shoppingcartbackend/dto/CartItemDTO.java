package com.app.shoppingcartbackend.dto;

import com.app.shoppingcartbackend.model.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private Long id;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Product product;
}
