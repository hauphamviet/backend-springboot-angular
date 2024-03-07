package com.example.ogani.model.request;

import jakarta.validation.constraints.Email;
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
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "Username rong")
    @NotEmpty(message = "Username rong")
    @Size(min = 5, max = 30, message = "Username tu 5-30 ky tu")
    private String username;

    @NotNull(message = "Email rong")
    @NotEmpty(message = "Email rong")
    @Size(min = 5, max = 30, message = "Email tu 5-30 ky tu")
    @Email(message = "Email khong hop le")
    private String email;

    @NotNull(message = "Mat khau rong")
    @NotEmpty(message = "Mat khau rong")
    @Size(min = 6, max = 30, message = "Mat khau tu 6-30 ky tu")
    private String password;

    private Set<String> role;

}
