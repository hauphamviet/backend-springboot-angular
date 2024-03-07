package com.example.ogani.controller;

import com.example.ogani.entity.CategoryEntity;
import com.example.ogani.model.request.CategoryRequest;
import com.example.ogani.model.response.CategoryResponse;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.service.categories.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    @Operation(summary = "Lay ra tat ca danh sach danh muc")
    public ResponseEntity<?> geAllCategories() {
        List<CategoryResponse>  categoryEntities = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryEntities);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Lay ra danh muc bang ten")
    public CategoryEntity getCategoryByName(@PathVariable String name) {
        return categoryService.findByNameContaining(name);
    }

    @GetMapping("/enabled")
    @Operation(summary = "Lay ra danh sach danh muc da kich hoat")
    public ResponseEntity<List<CategoryEntity>> getListEnabled() {
        List<CategoryEntity>  categoryEntities = categoryService.getListEnabled();
        return ResponseEntity.ok(categoryEntities);
    }

    @PostMapping("/create")
    @Operation(summary = "Tao moi danh muc")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        var categoryEntities = categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok(categoryEntities);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Tim danh muc bang id va cap nhat danh muc do")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        var categoryEntities = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.ok(categoryEntities);
    }

    @PutMapping("enable/{id}")
    @Operation(summary = "Kich hoat danh muc bang id")
    public ResponseEntity<?> enabled(@PathVariable long id) {
        categoryService.enableCategory(id);
        return ResponseEntity.ok(new MessageResponse("Cap nhat thanh cong"));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Xoa danh muc bang id")
    public ResponseEntity<?> delete(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new MessageResponse("Xoa thang cong"));
    }

}
