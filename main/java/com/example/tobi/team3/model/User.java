package com.example.tobi.team3.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class User {
    private String userId;
    private String password;
    private String userName;
    private String email;
    private String phone;
    private String address;
}
