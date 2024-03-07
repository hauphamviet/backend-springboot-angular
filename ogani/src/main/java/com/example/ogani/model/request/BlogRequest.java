package com.example.ogani.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {

    @NotNull(message="Tiêu đề rỗng")
    @NotEmpty(message = "Tiêu đề rỗng")
    @Size(min=5,max=300,message="Độ dài tiêu đề từ 1-300 ký tự")
    private String title;


    @NotNull(message = "Mô tả rỗng")
    @NotEmpty(message = "Mô tả rỗng")
    @Size(min=5,max=300,message= "Độ dài mô tả từ 1-300 ký tự")
    private String description;

    @NotNull(message="Nội dung rỗng")
    @NotEmpty(message="Nội dung rỗng")
    @Size(min=5,message="Độ dài nội dung tối thiểu từ 5 ký tự")
    private String content;

    @NotNull(message="Ảnh đang rỗng")
//    @NotEmpty(message="Ảnh đang rỗng")
    private Long imageId;

    private String username;

    private Set<Long> tags = new HashSet<>();

}
