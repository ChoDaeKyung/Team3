package com.example.tobi.team3.dto;

import com.example.tobi.team3.model.Items;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ItemListResponseDTO {
    List<Items> items;
}
