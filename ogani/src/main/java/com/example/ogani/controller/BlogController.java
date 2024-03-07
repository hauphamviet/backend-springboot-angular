package com.example.ogani.controller;

import com.example.ogani.entity.BlogEntity;
import com.example.ogani.model.request.BlogRequest;
import com.example.ogani.model.response.BlogResponse;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.service.blogs.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/")
    @Operation(summary = "Lấy tất cả danh sách blog")
    public ResponseEntity<List<BlogResponse>> getList() {
        List<BlogResponse> list = blogService.getList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy Blog ra bằng Id")
    public ResponseEntity<BlogEntity> getBlogById(@PathVariable long id) {
        var blogEntity = blogService.getBlogById(id);
        return ResponseEntity.ok(blogEntity);
    }

    @GetMapping("/newest")
    @Operation(summary = "Lấy ra danh sách blog mới nhất với số lượng = limit")
    public ResponseEntity<List<BlogEntity>> getListNewest(int limit) {
        List<BlogEntity> list = blogService.getListNewest(limit);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    @Operation(summary = "Tạo mới blog")
    public void createBlog(@RequestBody @Valid BlogRequest blogRequest) {
       blogService.createBlog(blogRequest);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Tìm Blog bằng id và cập nhật blog đó")
    public ResponseEntity<BlogEntity> updateBlog(@PathVariable long id, @RequestBody @Valid BlogRequest blogRequest) {
        var blogEntity = blogService.updateBlog(id, blogRequest);
        return ResponseEntity.ok(blogEntity);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Xoá blog bằng id")
    public ResponseEntity<?> deleteBlog(@PathVariable long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok(new MessageResponse("Delete success"));
    }

}
