package com.login.jwt.dto;

import com.login.jwt.domain.UserCommand;
import lombok.Getter;
import lombok.Setter;


public class UserDto {

    @Setter
    @Getter
    public static class RegisterUserRequest{

        private String username;
        private String password;
        private String email;

        public UserCommand toCommand(){
            return UserCommand.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }



}