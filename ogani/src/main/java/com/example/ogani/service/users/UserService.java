package com.example.ogani.service.users;

import com.example.ogani.entity.UserEntity;
import com.example.ogani.model.request.LoginRequest;
import com.example.ogani.model.request.PasswordRequest;
import com.example.ogani.model.request.ProfileRequest;
import com.example.ogani.model.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    void register(UserRequest userRequest);

    UserEntity getUserByUsername(String username);

    UserEntity updateUser(ProfileRequest request);

    void changePassword(PasswordRequest request);

    boolean comparePassword(String oldPassword, String dbPassword);

}
