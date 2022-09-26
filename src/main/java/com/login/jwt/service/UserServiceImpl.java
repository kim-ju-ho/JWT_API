package com.login.jwt.service;


import com.login.jwt.domain.User;
import com.login.jwt.domain.UserCommand;
import com.login.jwt.domain.UserValidator;
import com.login.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void registerUser(UserCommand request) {

        // UserCommand 객체 validation check
        UserValidator.userValidator(request);
        // 회원 ID 중복 확인
        String username = request.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        // 패스워드 암호화
        String password = passwordEncoder.encode(request.getPassword());
        String email = request.getEmail();

        User user = new User(username, password, email);
        userRepository.save(user);
    }
}
