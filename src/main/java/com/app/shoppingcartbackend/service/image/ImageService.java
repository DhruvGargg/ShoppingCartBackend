package com.app.shoppingcartbackend.service.image;

import com.app.shoppingcartbackend.dto.ImageDTO;
import com.app.shoppingcartbackend.exception.ResourceNotFound.ResourceNotFoundException;
import com.app.shoppingcartbackend.model.Image;
import com.app.shoppingcartbackend.model.Product;
import com.app.shoppingcartbackend.repository.ImageRepository.ImageRepository;
import com.app.shoppingcartbackend.service.product.ProductService;
import com.app.shoppingcartbackend.service.product.ProductServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements ImageServiceInterface {

    private final ImageRepository imageRepository;
    private final ProductService productService;
//    private final ProductServiceInterface productService;

    ImageService(ImageRepository imageRepository, ProductService productService) {
        this.imageRepository = imageRepository;
        this.productService = productService;
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image Not Found with id: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id)
                .ifPresentOrElse(imageRepository::delete, () -> {
                    throw new ResourceNotFoundException("Image Not Found with id: " + id);
                });
    }

    @Override
    public List<ImageDTO> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDTO> savedImageDTO = new ArrayList<>();
        for(MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);
                String buildDownloadUrl = "/api/v1/images/image/download";
                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
                imageRepository.save(savedImage);
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
                imageDTO.setImageName(savedImage.getFileName());
                imageDTO.setImageId(savedImage.getId());
                savedImageDTO.add(imageDTO);
            }
            catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDTO;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e)  {
            throw new RuntimeException(e.getMessage());
        }
    }
}
