package com.example.tobi.team3.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Items {
    private String item;
    private String price;
    private String detail;
    private String seller;
}
