package com.example.tobi.team3.controller;

import com.example.tobi.team3.dto.ItemDetailResponseDTO;
import com.example.tobi.team3.model.Item;
import com.example.tobi.team3.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/main/category")
    public ResponseEntity<List<Item>> getItemsByCategory(@RequestParam("category") String category) {
        List<Item> itemList = itemService.getItemsByCategory(category);

        return ResponseEntity.ok(itemList);
    }

    @GetMapping("/main/category/{id}")
    public ItemDetailResponseDTO getItemDetail(@PathVariable("id") Long id) {
        System.out.println("id :: " + id);
        Item itemDetail = itemService.getItemDetail(id);
        return ItemDetailResponseDTO.builder()
                .id(itemDetail.getId())
                .item(itemDetail.getItem())
                .price(itemDetail.getPrice())
                .detail(itemDetail.getDetail())
                .seller(itemDetail.getSeller())
                .category(itemDetail.getCategory())
                .build();
    }
}
