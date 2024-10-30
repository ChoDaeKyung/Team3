package com.example.tobi.team3.service;

import com.example.tobi.team3.mapper.ItemMapper;
import com.example.tobi.team3.model.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;

    public List<Items> getItemList(String tablename){
        return itemMapper.getItemListByTable(tablename);
    }
}
