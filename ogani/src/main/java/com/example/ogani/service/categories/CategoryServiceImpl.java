package com.example.ogani.service.categories;

import com.example.ogani.common.Constants;
import com.example.ogani.entity.CategoryEntity;
import com.example.ogani.exception.BadRequestException;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.CategoryRequest;
import com.example.ogani.model.response.CategoryResponse;
import com.example.ogani.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategories() {
        var categoryList = categoryRepository.findAll(Sort.by("id").descending());
        return categoryList.stream().map(this::mapToCategoryResponse).toList();
    }

    private CategoryResponse mapToCategoryResponse(CategoryEntity categoryEntity) {
        return CategoryResponse.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .enable(categoryEntity.isEnable())
                .build();
    }

    @Override
    public List<CategoryEntity> getListEnabled() {
        return categoryRepository.findAllByEnabled();
    }

    @Override
    public CategoryEntity findByNameContaining(String name) {
        return categoryRepository.findByNameContaining(name)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.CATEGORY_NAME_NOT_EXIST, name)));

    }

    @Override
    public CategoryEntity createCategory(CategoryRequest categoryRequest) {
       if (categoryRepository.findByNameContaining(categoryRequest.getName()).isPresent()) {
           throw new BadRequestException(Constants.NAME_CATEGORY_ALREADY_EXIST);
       }
       return categoryRepository.save(buildCategories(categoryRequest));

    }

    private CategoryEntity buildCategories(CategoryRequest categoryRequest) {
        return CategoryEntity.builder()
                .name(categoryRequest.getName())
                .enable(false)
                .build();
    }

    @Override
    public CategoryEntity updateCategory(long id, CategoryRequest categoryRequest) {
        var categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.CATEGORY_ID_NOT_EXIST, id)));
        categoryEntity.setName(categoryRequest.getName());
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public void enableCategory(long id) {
        var categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.CATEGORY_ID_NOT_EXIST, id)));
        if (categoryEntity.isEnable()) {
            categoryEntity.setEnable(false);
        } else {
            categoryEntity.setEnable(true);
        }
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteCategory(long id) {
        var categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.ID_DELETE_CATEGORY_NOT_EXIST, id)));
        categoryRepository.delete(categoryEntity);
    }
}
