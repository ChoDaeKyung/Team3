package com.example.tobi.team3.service;

import com.example.tobi.team3.config.security.CustomUserDetails;
import com.example.tobi.team3.mapper.UserMapper;
import com.example.tobi.team3.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.signIn(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return CustomUserDetails.builder()
                .user(user)
                .build();
    }
}