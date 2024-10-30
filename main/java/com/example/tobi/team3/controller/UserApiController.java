package com.example.tobi.team3.controller;

import com.example.tobi.team3.dto.*;
import com.example.tobi.team3.model.User;
import com.example.tobi.team3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public ResponseEntity<SignUpResponseDTO> signUp(
            @RequestBody SignUpRequestDTO signUpRequestDTO) {
        userService.signUp(signUpRequestDTO.toUser(bCryptPasswordEncoder));
        return ResponseEntity.ok(
                SignUpResponseDTO.builder()
                        .url("/main/login")
                        .build()
        );
    }

    @PostMapping("/findId")
    public ResponseEntity<FindIdResponseDTO> findId(
            @RequestBody FindIdRequestDTO findIdRequestDTO
    ) {

        User usered = userService.findId(findIdRequestDTO.getUserName(), findIdRequestDTO.getPhone());
//        System.out.println("usered :: " + usered);
//
        if (usered.getUserName().equals(findIdRequestDTO.getUserName()) &&
                usered.getPhone().equals(findIdRequestDTO.getPhone())
        ) {

            FindIdResponseDTO responseDTO = FindIdResponseDTO.builder()
                    .message("성공!")
                    .url("/main/login")
                    .userId(usered.getUserId())
                    .build();

            return ResponseEntity.ok(
                    responseDTO
            );
        } else {
            return ResponseEntity.ok(
                    FindIdResponseDTO.builder()
                            .message("입력하신 정보가 틀렸습니다.\n 다시 시도해주세요")
                            .url("/main/findId")
                            .build()
            );
        }
    }

        @PutMapping("/changePw")
    public ResponseEntity<ChangePwResponseDTO> changePassword(
            @RequestBody ChangePwRequestDTO changePwRequestDTO) {
        System.out.println("changePwRequestDTO: " + changePwRequestDTO);
        userService.changePw(changePwRequestDTO.toUser(bCryptPasswordEncoder));
        return ResponseEntity.ok(
                ChangePwResponseDTO.builder()
                        .url("/main/login")
                        .message("비밀번호 변경 성공!\n로그인해주세요.")
                        .build());
    }
}