package com.example.ogani.controller;

import com.example.ogani.entity.TagEntity;
import com.example.ogani.model.request.TagRequest;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.model.response.TagResponse;
import com.example.ogani.service.tags.TagService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TagController {

    private final TagService tagService;

    @GetMapping("/")
    @Operation(summary = "Lấy ra danh sách nhãn")
    public ResponseEntity<List<TagResponse>> getList() {
        List<TagResponse> list = tagService.getListTag();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    @Operation(summary = "Tạo mới nhãn")
    public void createTag(@RequestBody @Valid TagRequest tagRequest) {
        tagService.createTag(tagRequest);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Tìm nhãn bằng id và cập nhật nó")
    public ResponseEntity<TagEntity> updateTag(@PathVariable long id,@RequestBody @Valid TagRequest tagRequest) {
        var tagEntity = tagService.updateTag(id, tagRequest);
        return ResponseEntity.ok(tagEntity);
    }

    @PutMapping("/enable/{id}")
    @Operation(summary = "Kích hoạt nhãn bằng id")
    public ResponseEntity<?> enable(@PathVariable long id) {
        tagService.enableTag(id);
        return ResponseEntity.ok(new MessageResponse("Enable tag success"));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Xoá nhãn bằng id")
    public ResponseEntity<?> deleteTag(@PathVariable long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok(new MessageResponse("Delete Tag success"));
    }

}
