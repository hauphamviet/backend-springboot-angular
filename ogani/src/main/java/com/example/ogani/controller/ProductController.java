package com.example.ogani.controller;

import com.example.ogani.entity.ProductEntity;
import com.example.ogani.model.request.ProductRequest;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.model.response.ProductResponse;
import com.example.ogani.service.products.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    @Operation(summary = "Lấy ra danh sách sản phẩm")
    public ResponseEntity<List<ProductResponse>> getList() {
        List<ProductResponse> list = productService.getList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy ra sản phẩm bằng id")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable long id) {
        var productEntity = productService.getProductsById(id);
        return ResponseEntity.ok(productEntity);
    }

    @PostMapping("/create")
    @Operation(summary = "Tạo mới một sản phầm")
    public ResponseEntity<ProductEntity> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        var productEntity = productService.createProduct(productRequest);
        return ResponseEntity.ok(productEntity);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Tìm sản phầm bằng id và cập nhật sản phẩm đó")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable long id, @Valid @RequestBody ProductRequest productRequest) {
        var productEntity = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok(productEntity);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Xoá sản phầm bằng id")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(new MessageResponse("Xoá sản phẩm thành công"));
    }

    @GetMapping("/price")
    @Operation(summary = "Lay ra danh sach 8 san pham co gia tu thap nhat den cao")
    public ResponseEntity<List<ProductEntity>> getListByPrice() {
        List<ProductEntity> list = productService.getListByPrice();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/category/{id}")
    @Operation(summary = "Lay ra danh sach san pham bang id cua danh muc")
    public ResponseEntity<List<ProductResponse>> getListProductByCategory(@PathVariable long id) {
        List<ProductResponse> list = productService.getListProductByCategory(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    @Operation(summary = "Tim kiem san pham bang keyword")
    public ResponseEntity<List<ProductEntity>> searchProduct(@RequestParam("keyword") String keyword) {
        List<ProductEntity> list = productService.searchProduct(keyword);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/range")
    @Operation(summary = "Lay ra danh sach san pham o cac muc gia tu min den max")
    public ResponseEntity<List<ProductEntity>> getListByPriceRange(@RequestParam("id") long id, @RequestParam("min") int min, @RequestParam("max") int max) {
        List<ProductEntity> list = productService.getListByPriceRange(id, min, max);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/related/{id}")
    @Operation(summary = "Lay ra ngau nhien 4 san pham bang category_id")
    public ResponseEntity<List<ProductEntity>> getListRelatedProduct(@PathVariable long id) {
        List<ProductEntity> list = productService.findRelatedProduct(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/newest/{number}")
    @Operation(summary = "Lay ra danh sach san pham moi nhat gioi han so luong = number")
    public ResponseEntity<List<ProductEntity>> getListNewest(@PathVariable int number) {
        List<ProductEntity> list = productService.getListNewest(number);
        return ResponseEntity.ok(list);
    }

}
