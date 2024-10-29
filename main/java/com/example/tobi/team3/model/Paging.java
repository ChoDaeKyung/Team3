package com.example.tobi.team3.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Paging {
    private String category;
    private int offset;
    private int size;
}
