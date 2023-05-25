package com.vn.app_service.security.services.users;

import com.vn.app_service.constant.enums.ApiStatus;
import com.vn.app_service.model.ERole;
import com.vn.app_service.model.Role;
import com.vn.app_service.model.User;
import com.vn.app_service.payload.request.LoginRequest;
import com.vn.app_service.payload.request.SignupRequest;
import com.vn.app_service.payload.response.BaseResponse;
import com.vn.app_service.repository.RoleRepository;
import com.vn.app_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Signup
     * @param signUpRequest
     * @return
     */
    public BaseResponse<?> registerUser(SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return BaseResponse.builder().code(ApiStatus.BAD_REQUEST.getCode()).message("Error: Username is already taken!").build();
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return BaseResponse.builder().code(ApiStatus.BAD_REQUEST.getCode()).message("Error: Email is already in use!").build();
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        user.setAddress(signUpRequest.getAddress());
        user.setPhone(signUpRequest.getPhone());
        userRepository.save(user);

        return BaseResponse.builder().code(ApiStatus.SUCCESS.getCode()).message("User registered successfully!").build();
    }
}
