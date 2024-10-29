package com.example.tobi.team3.mapper;

import com.example.tobi.team3.model.Item;
import com.example.tobi.team3.model.Likes;
import com.example.tobi.team3.model.Paging;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<Item> getItemListByCategory(Paging paging);
    Item getItemDetail(Long id);
    int saveItem(Item item);
    List<Item> getItemListByUserId(@Param("seller") String seller);
    void deleteItemListById(Long id);
    void itemLikes(Item item);
    void likesCount(Likes likes);
    int countCategoryItems(@Param("category") String category);
    List<Item> searchItem(String search);
}