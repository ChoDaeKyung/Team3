package com.example.tobi.team3.mapper;

import com.example.tobi.team3.model.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    User signIn(String userId);
    void signUp(User user);
    void changePw(User user);
    User findId(User user);
    void findUser(User user);
}