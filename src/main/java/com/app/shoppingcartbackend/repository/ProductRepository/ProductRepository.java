package com.app.shoppingcartbackend.repository.ProductRepository;

import com.app.shoppingcartbackend.model.Category;
import com.app.shoppingcartbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String category);

    List<Product> findByBrandName(String brand);
}
