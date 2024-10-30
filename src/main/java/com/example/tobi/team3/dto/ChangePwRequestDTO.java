package com.example.tobi.team3.dto;

import com.example.tobi.team3.model.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@ToString
public class ChangePwRequestDTO {
    private String userId;
    private String userName;
    private String phone;
    private String password;

    public User toUser(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return User.builder()
                .userId(userId)
                .password(bCryptPasswordEncoder.encode(password))
                .userName(userName)
                .phone(phone)
                .build();
    }
}
