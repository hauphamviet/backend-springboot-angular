package com.example.ogani.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    private String username;

    private String firstname;

    private String lastname;

    private String email;

    private String country;

    private String state;

    private String address;

    private String phone;
}
