package com.example.ogani.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotNull(message = "Ten san pham rong")
    @NotEmpty(message = "Ten san pham rong")
    @Size(min = 5, max = 50, message = "Ten san pham tu 3-50 ky tu")
    private String name;

    @NotNull(message = "Mo ta rong")
    @NotEmpty(message = "Mo ta rong")
    @Size(min = 5, max = 1000, message = "Mo ta san pham tu 5-1000 ky tu")
    private String description;

    @NotNull(message = "Gia tien rong")
    @Min(value = 0, message = "Gia tien san pham lon hon 0")
    private long price;

    @NotNull(message = "So luong san pham")
    @Min(message = "So luong san pham tu 0", value = 0)
    private int quantity;

    @NotNull(message = "Danh muc rong")
    private long categoryId;

    @NotNull(message = "Anh san pham rong")
    private Set<Long> imageIds;

}
