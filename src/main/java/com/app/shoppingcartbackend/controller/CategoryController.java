package com.app.shoppingcartbackend.controller;

import com.app.shoppingcartbackend.exception.AlreadyExistsException.AlreadyExistsException;
import com.app.shoppingcartbackend.exception.ResourceNotFound.ResourceNotFoundException;
import com.app.shoppingcartbackend.model.Category;
import com.app.shoppingcartbackend.response.APIResponse;
import com.app.shoppingcartbackend.service.category.CategoryService;
import com.app.shoppingcartbackend.service.category.CategoryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.support.ReactivePageableExecutionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("{api.prefix}/category")
public class CategoryController {
    private final CategoryServiceInterface categoryService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new APIResponse("Categories fetched successfully!", categories));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new APIResponse("Error", INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addCategory(@RequestBody Category category) {
        try {
            Category savedCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new APIResponse("Category added successfully!", savedCategory));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new APIResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<APIResponse> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoriesById(id);
            return ResponseEntity.ok(new APIResponse("Category fetched successfully!", category));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/category/{name}/")
    public ResponseEntity<APIResponse> getCategoryByName(@PathVariable String name) {
        try {
            Category category = categoryService.getCategoriesByName(name);
            return ResponseEntity.ok(new APIResponse("Category fetched successfully!", category));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<APIResponse> deleteCategoryById(@PathVariable Long id) {
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new APIResponse("Category deleted successfully!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }

    public ResponseEntity<APIResponse> updateCategoryById(@PathVariable Long id, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new APIResponse("Category updated successfully!", updatedCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }
}
