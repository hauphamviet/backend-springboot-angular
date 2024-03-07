package com.example.ogani.model.response;

import com.example.ogani.entity.OrderDetailEntity;
import com.example.ogani.entity.UserEntity;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private long id;
    private String firstname;
    private String lastname;
    private String country;
    private String address;
    private String town;
    private String state;
    private long postCode;
    private String email;
    private String phone;
    private String note;
    private long totalPrice;
    private UserEntity user;
    private Set<OrderDetailEntity> orderDetails;
}
