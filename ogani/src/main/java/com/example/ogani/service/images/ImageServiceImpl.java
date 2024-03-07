package com.example.ogani.service.images;

import com.example.ogani.common.Constants;
import com.example.ogani.entity.ImageEntity;
import com.example.ogani.exception.BadRequestException;
import com.example.ogani.exception.InternalServerException;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.response.ImageResponse;
import com.example.ogani.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/photos/";

    @Override
    public List<ImageResponse> getListImage() {
        var imageList = imageRepository.findAll(Sort.by("id").descending());
        return imageList.stream().map(this::mapToImageResponse).toList();
    }

    private ImageResponse mapToImageResponse(ImageEntity imageEntity) {
            return ImageResponse.builder()
                    .id(imageEntity.getId())
                    .name(imageEntity.getName())
                    .size(imageEntity.getSize())
                    .data(imageEntity.getData())
                    .uploadedBy(imageEntity.getUploadedBy())
                    .build();
    }

    @Override
    public ImageEntity getImageById(long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.IMAGE_ID_NOT_EXIST, id)));
    }

    @Override
    public ImageEntity uploadFile(MultipartFile file) {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);;
        if (originalFilename != null && originalFilename.length() > 0) {
            if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
                throw new BadRequestException("Không hỗ trợ định dạng file này");
            }
            try {
                var img = new ImageEntity();
                img.setName(file.getName());
                img.setSize(file.getSize());
                img.setType(extension);
                img.setData(file.getBytes());

                String uid = UUID.randomUUID().toString();
                String link = UPLOAD_DIR + uid + "." + extension;
                // Create file
                File serverFile = new File(link);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                return imageRepository.save(img);

            } catch (Exception e) {
                throw new InternalServerException("Loi khi upload file");
            }
        }
        throw new BadRequestException("File khong hop le");
    }

    @Override
    public List<ImageEntity> getListByUser(long useId) {
        return imageRepository.getListImageOfUser(useId);
    }

    @Override
    public void deleteImage(long id) {
        var imageEntity = imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.IMAGE_ID_NOT_EXIST, id)));
        imageRepository.delete(imageEntity);
    }
}
