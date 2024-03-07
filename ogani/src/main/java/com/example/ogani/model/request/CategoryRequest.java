package com.example.ogani.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    @NotNull(message = "Ten danh muc rong")
    @NotEmpty(message = "Ten danh muc rong")
    @Size(min = 5, max = 50, message = "Do dai danh muc tu 5-50 ky tu")
    private String name;

}
