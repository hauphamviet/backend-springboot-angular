package com.example.ogani.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotNull(message = "Ho khach hang rong")
    @NotEmpty(message = "Ho khach hang rong")
    @Size(min = 3, max = 50, message = "Ho khach hang tu 3-50 ky tu")
    private String firstname;

    @NotNull(message = "Ten khach hang rong")
    @NotEmpty(message = "Ten khach hang rong")
    @Size(min = 3, max = 50, message = "Ten khach hang tu 3-50 ky tu")
    private String lastname;

    @NotNull(message = "Ten quoc gia rong")
    @NotEmpty(message = "Ten quoc gia rong")
    private String country;

    @NotNull(message = "Ten dia chi rong")
    @NotEmpty(message = "Ten dia chi rong")
    private String address;

    @NotNull(message = "Ten quoc gia rong")
    @NotEmpty(message = "Ten quoc gia rong")
    private String town;

    @NotNull(message = "Ten khu vuc rong")
    @NotEmpty(message = "Ten khu vuc rong")
    private String state;

    @NotNull(message = "Ma buu dien rong")
    @NotEmpty(message = "Ma buu dien rong")
    private long postCode;

    @NotNull(message = "Email rong")
    @NotEmpty(message = "Email rong")
    @Email(message = "Email khong dung dinh dang")
    private String email;

    @NotNull(message = "So dien thoai rong")
    @NotEmpty(message = "So dien thoai rong")
    private String phone;

    private String note;

    private long totalPrice;

    private String username;

    private List<OrderDetailRequest> orderDetails;

}
