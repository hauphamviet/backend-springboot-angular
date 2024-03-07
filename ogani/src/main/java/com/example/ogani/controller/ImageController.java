package com.example.ogani.controller;

import com.example.ogani.entity.ImageEntity;
import com.example.ogani.exception.BadRequestException;
import com.example.ogani.exception.InternalServerException;
import com.example.ogani.model.response.ImageResponse;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.service.images.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private static String UPLOAD_DIR  = System.getProperty("user.dir") + "/src/main/resources/static/photos/";

    @GetMapping("/")
    @Operation(summary = "Lay ra danh sach hinh anh")
    public ResponseEntity<?> getList() {
        List<ImageResponse>  listImage = imageService.getListImage();
        return ResponseEntity.ok(listImage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lay ra danh sach hinh anh bang id")
    public ResponseEntity<?> getListById(@PathVariable long id) {
        var imageEntity = imageService.getImageById(id);
        return ResponseEntity.ok(imageEntity);
    }

    @GetMapping("/user/{id}")
    @Operation(summary="Lấy ra danh sách hình ảnh của user bằng user_id")
    public ResponseEntity<?> getListByUser(@PathVariable long userId){
        List<ImageEntity> listImage = imageService.getListByUser(userId);

        return ResponseEntity.ok(listImage);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Xoa hinh anh bang id")
    public ResponseEntity<?> deleteImage(@PathVariable long id) {
         imageService.deleteImage(id);
         return ResponseEntity.ok(new MessageResponse("Xoa anh thanh cong!"));
    }

    @PostMapping("/upload-file")
    @Operation(summary = "Upload file len database")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        var imageEntity = imageService.uploadFile(file);
        return ResponseEntity.ok(imageEntity);
    }

}
