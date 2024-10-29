package com.example.tobi.team3.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangePwResponseDTO {
    private String url;
    private String message;
}
