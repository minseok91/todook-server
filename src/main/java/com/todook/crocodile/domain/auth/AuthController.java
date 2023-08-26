package com.todook.crocodile.domain.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todook.crocodile.domain.auth.user.dto.User;
import com.todook.crocodile.presentation.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    @GetMapping("/user")
    public ApiResponse<User> user(HttpServletRequest request) {
        final User userDto = (User)request.getAttribute("user");
        return ApiResponse.<User>builder()
                .data(userDto)
                .build();
    }
}

