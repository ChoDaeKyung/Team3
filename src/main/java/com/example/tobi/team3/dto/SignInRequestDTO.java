package com.example.tobi.team3.dto;

import com.example.tobi.team3.model.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignInRequestDTO {
    private String userId;
    private String password;

    public User toUser() {
        return User.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}
