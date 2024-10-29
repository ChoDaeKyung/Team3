package com.example.tobi.team3.config;

import com.example.tobi.team3.config.security.CustomAuthenticationFailureHandler;
import com.example.tobi.team3.config.security.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/static/**", "/css/**", "/js/**"
                );
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            CustomAuthenticationSuccessHandler successHandler,
            CustomAuthenticationFailureHandler failureHandler
    ) throws Exception {
        http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        new AntPathRequestMatcher("/main/login"),
                                        new AntPathRequestMatcher("/main/join"),
                                        new AntPathRequestMatcher("/join"),
                                        new AntPathRequestMatcher("/main"),
                                        new AntPathRequestMatcher("/main/find"),
                                        new AntPathRequestMatcher("/main/find/findId"),
                                        new AntPathRequestMatcher("/main/find/changePw"),
                                        new AntPathRequestMatcher("/changePw"),
                                        new AntPathRequestMatcher("/findId"),
                                        new AntPathRequestMatcher("/main/category"),
                                        new AntPathRequestMatcher("/api/main/category"),
                                        new AntPathRequestMatcher("/main/category/detail"),
                                        new AntPathRequestMatcher("/api/main/category/{id}"),
                                        new AntPathRequestMatcher("/api/main/category/{id}"),
                                        new AntPathRequestMatcher("/api/exchange-rates"),
                                        new AntPathRequestMatcher("/api/likes"),
                                        new AntPathRequestMatcher("/main/search"),
                                        new AntPathRequestMatcher("/api/search/**"),
                                        new AntPathRequestMatcher("/uploads/**")
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/main/login")
                                .loginProcessingUrl("/login")
                                .successHandler(successHandler)
                                .failureHandler(failureHandler)
                )
                .logout(
                        logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}