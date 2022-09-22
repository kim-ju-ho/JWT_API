package com.login.jwt.controller;

import com.login.jwt.domain.User;
import com.login.jwt.domain.UserCommand;
import com.login.jwt.dto.UserDto;
import com.login.jwt.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public void registerUser(UserDto.RegisterUserRequest request) {
        UserCommand userCommand =request.toCommand();
        userService.registerUser(userCommand);

    }



}