package com.example.ogani.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private long id;
    private String name;
    private boolean enable;

}
