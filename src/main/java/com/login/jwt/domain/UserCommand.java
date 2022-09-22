package com.login.jwt.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
public class UserCommand {
    private final String username;
    private final String password;
    private final String email;

    public UserCommand(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
