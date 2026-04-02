package com.app.shoppingcartbackend.dto;

import lombok.Data;

@Data
public class ImageDTO {
    private Long id;
    private String fileName;
    private String downloadUrl;
}
