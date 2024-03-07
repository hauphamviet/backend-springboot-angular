package com.example.ogani.model.response;

import com.example.ogani.entity.CategoryEntity;
import com.example.ogani.entity.ImageEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private long id;
    private String name;
    private String description;
    private long price;
    private int quantity;
    private CategoryEntity categoryEntity;
    private Set<ImageEntity> imageEntities = new HashSet<>();

}
