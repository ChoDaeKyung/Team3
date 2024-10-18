package com.example.tobi.team3.mapper;

import com.example.tobi.team3.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<Item> getItemListByCategory(@Param("category") String category);
    Item getItemDetail(Long id);
}

