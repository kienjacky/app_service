package com.vn.app_service.controllers;

import com.vn.app_service.payload.response.BaseResponse;
import com.vn.app_service.security.services.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserControler {

    private final UserService userService;

    public UserControler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAll() {

        BaseResponse<?> baseResponse = userService.getAll();

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
