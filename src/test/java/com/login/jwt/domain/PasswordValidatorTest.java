package com.login.jwt.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PasswordValidatorTest {

    @DisplayName("유효한 비밀번호입니다.")
    @Test
    void validator(){
        assertThatCode(()->new PasswordValidator("Abcdefghijk1@"))
                . doesNotThrowAnyException();
    }

    @DisplayName("유효하지 않은 비밀번호입니다.")
    @ParameterizedTest
    @ValueSource(strings ={"abc","abcdefghijk","abcdefghijk@","Abcdefghijk@"})
    @Test
    void validator2(String password){
        assertThatCode(()->new PasswordValidator(password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 비밀번호입니다.")
                ;
    }

}