package com.login.jwt.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserInfoDto {

    private final String username;
    private final String email;

}
