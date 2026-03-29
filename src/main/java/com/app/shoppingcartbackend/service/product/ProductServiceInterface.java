package com.app.shoppingcartbackend.service.product;

import com.app.shoppingcartbackend.model.Product;
import com.app.shoppingcartbackend.request.AddProductRequest;
import com.app.shoppingcartbackend.request.ProductUpdateRequest;

import java.util.List;

public interface ProductServiceInterface {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
