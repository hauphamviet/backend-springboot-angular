package com.example.ogani.model.response;

import com.example.ogani.entity.ImageEntity;
import com.example.ogani.entity.TagEntity;
import com.example.ogani.entity.UserEntity;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {

    private long id;
    private String title;
    private String description;
    private String content;
    private Timestamp createAt;
    private ImageEntity image;
    private UserEntity user;
    private Set<TagEntity> tags = new HashSet<>();

}
