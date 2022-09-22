package com.login.jwt.service;


import com.login.jwt.domain.UserCommand;

public interface UserService {

    void registerUser(UserCommand request);
}