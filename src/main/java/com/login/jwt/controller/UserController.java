package com.login.jwt.controller;

import com.login.jwt.domain.User;
import com.login.jwt.domain.UserRoleEnum;
import com.login.jwt.dto.SignupRequestDto;
import com.login.jwt.dto.UserInfoDto;
import com.login.jwt.security.UserDetailsImpl;
import com.login.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    //private final KakaoUserService kakaoUserService;

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public User registerUser(SignupRequestDto requestDto) {
        return userService.registerUser(requestDto);

    }

    // 회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDto(username, isAdmin);
    }

//    @GetMapping("/user/kakao/callback")
//    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
//        kakaoUserService.kakaoLogin(code);
//        return "redirect:/";
//    }
}