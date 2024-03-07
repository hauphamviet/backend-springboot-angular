package com.example.ogani.model.response;

import com.example.ogani.entity.RoleEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String country;
    private String state;
    private String address;
    private String phone;
    private String verificationCode;
    private boolean enabled;
    private Set<RoleEntity> roles = new HashSet<>();

}
