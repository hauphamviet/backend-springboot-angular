package com.example.ogani.service.categories;

import com.example.ogani.entity.CategoryEntity;
import com.example.ogani.model.request.CategoryRequest;
import com.example.ogani.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    List<CategoryEntity> getListEnabled();

    CategoryEntity findByNameContaining(String name);

    CategoryEntity createCategory(CategoryRequest categoryRequest);

    CategoryEntity updateCategory(long id, CategoryRequest categoryRequest);

    void enableCategory(long id);

    void deleteCategory(long id);

}
