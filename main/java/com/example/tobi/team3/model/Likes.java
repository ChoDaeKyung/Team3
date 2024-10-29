package com.example.tobi.team3.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Likes {
    private String userId;
    private int itemId;
}
