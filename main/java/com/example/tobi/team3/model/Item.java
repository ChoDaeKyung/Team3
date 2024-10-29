package com.example.tobi.team3.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Id;
import javax.persistence.Version;

@Getter
@Builder
public class Item {
    @Id
    private String id;
    private String item;
    private String price;
    private String detail;
    private String seller;
    private String category;
    private String imagePath;
    private int likes;
    @Version
    private int version;
}
