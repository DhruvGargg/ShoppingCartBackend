package com.app.shoppingcartbackend.service.image;

import com.app.shoppingcartbackend.dto.ImageDTO;
import com.app.shoppingcartbackend.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageServiceInterface {

    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDTO> saveImage(List<MultipartFile> image, Long productId);
    void updateImage(MultipartFile image, Long imageId);
}
