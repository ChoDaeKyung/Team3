package com.example.tobi.team3.service;

import com.example.tobi.team3.mapper.ItemMapper;
import com.example.tobi.team3.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;

    public List<Item> getItemsByCategory(String category) {
        return itemMapper.getItemListByCategory(category);
    }

    public Item getItemDetail(Long id) {
        return itemMapper.getItemDetail(id);
    }
}
