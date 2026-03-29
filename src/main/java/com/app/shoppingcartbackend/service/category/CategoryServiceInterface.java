package com.app.shoppingcartbackend.service.category;

import com.app.shoppingcartbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryServiceInterface {
    Category getCategoriesById(Long id);
    Category getCategoriesByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategoryById(Long id);
}
