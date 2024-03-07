package com.example.ogani.service.users;

import com.example.ogani.common.Constants;
import com.example.ogani.entity.RoleEntity;
import com.example.ogani.entity.UserEntity;
import com.example.ogani.enums.RoleEnum;
import com.example.ogani.exception.BadRequestException;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.LoginRequest;
import com.example.ogani.model.request.PasswordRequest;
import com.example.ogani.model.request.ProfileRequest;
import com.example.ogani.model.request.UserRequest;
import com.example.ogani.model.response.UserInfoResponse;
import com.example.ogani.repository.RoleRepository;
import com.example.ogani.repository.UserRepository;
import com.example.ogani.security.jwt.JwtUtils;
import com.example.ogani.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public void register(UserRequest userRequest) {
        var userEntity = new UserEntity();
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Set<String> strRoles = userRequest.getRole();
        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null) {
            var userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        var adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        var modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        var userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        userEntity.setRoles(roles);
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(Constants.USER_NAME_NOT_EXIST));
    }

    @Override
    public UserEntity updateUser(ProfileRequest request) {
        var userEntity = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException(Constants.USER_NAME_NOT_EXIST));
        userEntity.setFirstname(request.getFirstname());
        userEntity.setLastname(request.getLastname());
        userEntity.setEmail(request.getEmail());
        userEntity.setCountry(request.getCountry());
        userEntity.setState(request.getState());
        userEntity.setAddress(request.getAddress());
        userEntity.setPhone(request.getPhone());

        return userRepository.save(userEntity);
    }

    @Override
    public boolean comparePassword(String oldPassword, String dbPassword) {
        return passwordEncoder.matches(oldPassword, dbPassword);
    }


    @Override
    public void changePassword(PasswordRequest request) {
//        var userEntity = userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new NotFoundException(Constants.USER_NAME_NOT_EXIST + request.getUsername()));
        var userEntity = getUserByUsername(request.getUsername());
        var compareTemp = comparePassword(request.getOldPassword(), userEntity.getPassword());
        if (!compareTemp) {
            throw new BadRequestException(Constants.PASSWORD_DOES_NOT_MATCH);
        }
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new BadRequestException(Constants.NEW_PASSWORD_NOT_MATCH);
        }
        userEntity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(userEntity);


//        if (encoder.encode(request.getOldPassword()) != userEntity.getPassword()) {
//            throw new BadRequestException("Mat khau cu khong dung");
//        }
//        userEntity.setPassword(encoder.encode(request.getNewPassword()));
//
//        userRepository.save(userEntity);
    }
}
