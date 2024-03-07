package com.example.ogani.controller;

import com.example.ogani.entity.UserEntity;
import com.example.ogani.model.request.PasswordRequest;
import com.example.ogani.model.request.ProfileRequest;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.service.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    @Operation(summary = "Lay ra user bang username")
    public ResponseEntity<UserEntity> getUser(String username) {
        var userEntity = userService.getUserByUsername(username);
        return ResponseEntity.ok(userEntity);
    }

    @PutMapping("/update")
    @Operation(summary = "Cap nhat user")
    public ResponseEntity<UserEntity> updateProfile(@Valid ProfileRequest request) {
        var userEntity = userService.updateUser(request);
        return ResponseEntity.ok(userEntity);
    }

    @PostMapping("/password")
    @Operation(summary = "Thay doi pass")
    public ResponseEntity<?> changePassword(@RequestBody @Valid PasswordRequest request) {
        userService.changePassword(request);
        return ResponseEntity.ok(new MessageResponse("Thay doi mat khau thanh cong!"));
    }

}
