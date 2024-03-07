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
public class OrderDetailRequest {

    @NotNull(message = "Ten san pham rong")
    @NotEmpty(message = "Ten san pham rong")
    @Size(min = 5, max = 50, message = "Ten san pham tu 5-50 ky tu")
    private String name;

    @NotNull(message = "Gia san pham rong")
    @NotEmpty(message = "Gia san pham rong")
    @Size(min = 0, message = "Gia san pham tu 0 tro len")
    private long price;

    @NotNull(message = "So luong san pham rong")
    @NotEmpty(message = "So luong san pham rong")
    @Size(min = 1, message = "So luong san pham tu 1 tro len")
    private int quantity;

    private long subTotal;

}
