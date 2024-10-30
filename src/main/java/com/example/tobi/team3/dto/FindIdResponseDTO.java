package com.example.tobi.team3.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindIdResponseDTO {
    private String userId;
    private String url;
    private String message;
}