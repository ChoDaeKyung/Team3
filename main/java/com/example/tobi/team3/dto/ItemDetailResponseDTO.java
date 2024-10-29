package com.example.tobi.team3.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDetailResponseDTO {
    private String id;
    private String item;
    private String price;
    private String detail;
    private String seller;
    private String category;
    private String imagePath;
    private int likes;
    private int version;
}
