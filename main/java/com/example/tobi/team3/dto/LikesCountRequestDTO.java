package com.example.tobi.team3.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikesCountRequestDTO {
    private String userId;
    private int itemId;
}
