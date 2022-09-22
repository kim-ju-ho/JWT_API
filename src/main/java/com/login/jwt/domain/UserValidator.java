package com.login.jwt.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;

@Getter
@Slf4j
public class UserValidator {

    private final char[] AllowedCharacterRule = {'!','@','#','$','%','^','&','*'};

    public static void userValidator(UserCommand userCommand){
        usernameValidator(userCommand.getUsername());
        passwordValidator(userCommand.getPassword());
        emailValidator(userCommand.getEmail());
    }

    public static void usernameValidator(String username){
        if(username.length()<8||username.length()>13){
            throw new IllegalArgumentException("");
        }
    }
    public static void passwordValidator(String password) {
        org.passay.PasswordValidator passwordValidator = new org.passay.PasswordValidator(
                new LengthRule(8,16),
                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1),

                new WhitespaceRule()

        );
        PasswordData passwordData = new PasswordData(password);
        RuleResult ruleResult = passwordValidator.validate(passwordData);

        if(ruleResult.isValid()){
            log.info("유효한 비밀번호입니다.");
        }else{
            log.info("[password error]");

            for (String msg : passwordValidator.getMessages(ruleResult)) {
                log.info("[error msg] {}",msg);
            }
            throw new IllegalArgumentException("유효하지 않은 비밀번호입니다.");
        }
    }

    public static void emailValidator(String email){

    }



}
