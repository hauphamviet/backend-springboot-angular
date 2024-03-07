package com.example.ogani.model.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagResponse {

    private long id;
    private String name;
    private boolean enable;

}
