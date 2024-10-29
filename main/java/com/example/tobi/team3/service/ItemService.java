package com.example.tobi.team3.service;

import com.example.tobi.team3.dto.SearchResponseDTO;
import com.example.tobi.team3.mapper.ItemMapper;
import com.example.tobi.team3.model.Item;
import com.example.tobi.team3.model.Likes;
import com.example.tobi.team3.model.Paging;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;
    private final ImageService imageService;

    public List<Item> getItemsByCategory(@Param("category") String category, int page, int size) {
        int offset = (page - 1) * size;
        return itemMapper.getItemListByCategory(
                Paging.builder()
                        .category(category)
                        .offset(offset)
                        .size(size)
                        .build());
    }

    public Item getItemDetail(Long id) {
        return itemMapper.getItemDetail(id);
    }

    public int getTotalCategory(String category) {
        return itemMapper.countCategoryItems(category); // 총 게시글 수 반환
    }

    public List<Item> getItemsByUserId(String seller) {
        return itemMapper.getItemListByUserId(seller);
    }

    public void itemLikes(String id, int version) {
        itemMapper.itemLikes(
                Item.builder()
                        .id(id)
                        .version(version)
                        .build()
        );
        System.out.println("Now ID : " + id);
        System.out.println("Now Version : " + version);
    }

    public void saveItem(String item, String imagePath, String detail, String seller, String price, String category) {
        int likes = 0;
        Item item1 = Item.builder()
                .item(item)
                .imagePath(imagePath)
                .detail(detail)
                .seller(seller)
                .price(price)
                .category(category)
                .likes(likes)
                .build();
        int result = itemMapper.saveItem(item1);
        if (result > 0) {
            // 성공 처리
            System.out.println("성공함" + result);
            // 필요 시 추가 로직 (예: 알림, 리다이렉트 등)
        } else {
            // 실패 처리
            System.out.println("실패함" + result);
            // 예외 발생 또는 사용자에게 알림
            throw new RuntimeException("Failed to save item.");
        }
    }

    public void deleteItemListById(long id) {
        itemMapper.deleteItemListById(id);
    }

    public void likesCount(String userId, int itemId) {
        itemMapper.likesCount(
                Likes.builder()
                        .userId(userId)
                        .itemId(itemId)
                        .build()
        );
    }

    public List<SearchResponseDTO> searchItem(String search) {
        List<Item> items = itemMapper.searchItem(search);
        List<SearchResponseDTO> searchResponseDTOS = new ArrayList<>();

        for (Item item : items) {
            SearchResponseDTO image = imageService.getImage(item.getImagePath());
            searchResponseDTOS.add(
                    SearchResponseDTO.builder()
                            .id(item.getId())
                            .item(item.getItem())
                            .price(item.getPrice())
                            .detail(item.getDetail())
                            .seller(item.getSeller())
                            .category(item.getCategory())
                            .base64Image(image == null ? null : image.getBase64Image())
                            .contentType(image == null ? null : image.getContentType())
                            .build()
            );

        }

        return searchResponseDTOS;

    }
}
