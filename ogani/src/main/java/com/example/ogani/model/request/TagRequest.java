package com.example.ogani.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagRequest {

    @NotNull(message = "Tên nhãn rỗng")
    @NotEmpty(message = "Tên nhãn rỗng")
    @Schema(description = "Tên nhãn", example = "Beauty", required = true)
    private String name;

}
