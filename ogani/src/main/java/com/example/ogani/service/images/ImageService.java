package com.example.ogani.service.images;

import com.example.ogani.entity.ImageEntity;
import com.example.ogani.model.response.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<ImageResponse> getListImage();

    ImageEntity getImageById(long id);

    ImageEntity uploadFile(MultipartFile file);

    List<ImageEntity> getListByUser(long useId);

    void deleteImage(long id);

}
