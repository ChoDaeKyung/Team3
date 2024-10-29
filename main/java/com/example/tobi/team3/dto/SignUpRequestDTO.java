package com.example.tobi.team3.dto;

import com.example.tobi.team3.model.User;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class    SignUpRequestDTO {
    private String userId;
    private String password;
    private String userName;
    private String email;
    private String phone;
    private String address;

    public User toUser(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return User.builder()
                .userId(userId)
                .password(bCryptPasswordEncoder.encode(password))
                .userName(userName)
                .email(email)
                .phone(phone)
                .address(address)
                .build();
    }
}
