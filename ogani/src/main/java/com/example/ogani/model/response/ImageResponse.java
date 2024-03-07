package com.example.ogani.model.response;

import com.example.ogani.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {

    private long id;
    private String name;
    private long size;
    private byte[] data;
    private UserEntity uploadedBy;

}
