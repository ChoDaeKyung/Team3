package com.example.tobi.team3.mapper;

import com.example.tobi.team3.model.Items;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<Items> getItemListByTable(@Param("tablename") String tablename);
}
