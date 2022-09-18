package com.login.jwt.domain;

import lombok.extern.slf4j.Slf4j;
import org.passay.*;

@Slf4j
public class PasswordValidator {
    private final String password;
    private final char[] AllowedCharacterRule = {'!','@','#','$','%','^','&','*'};


    public PasswordValidator(String password) {
        validator(password);
        this.password = password;
    }

    private void validator(String password) {
        System.out.println("들어오는 파라미터 값 ");
        System.out.println(password);
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

}
