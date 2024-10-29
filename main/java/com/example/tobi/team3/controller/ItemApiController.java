package com.example.tobi.team3.controller;

import com.example.tobi.team3.dto.*;
import com.example.tobi.team3.model.Item;
import com.example.tobi.team3.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/main/category")
    public ItemListResponseDTO getItemsByCategory(
            @RequestParam("category") String category,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "9") int size) {
        List<Item> itemList = itemService.getItemsByCategory(category, page, size);

        int totalItems = itemService.getTotalCategory(category);

        boolean last = (page * size) >= totalItems;
        return ItemListResponseDTO.builder()
                .items(itemList)
                .last(last)
                .build();
    }

    @GetMapping("/itemList")
    public ResponseEntity<List<Item>> deleteItem(@RequestParam("seller") String seller) {
        System.out.println(seller);
        List<Item> itemList = itemService.getItemsByUserId(seller);
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
                .imagePath(itemDetail.getImagePath())
                .likes(itemDetail.getLikes())
                .version(itemDetail.getVersion())
                .build();
    }

    @PostMapping("/addItem")
    public ResponseEntity<String> uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("item") String item,
            @RequestParam("detail") String detail,
            @RequestParam("seller") String seller,
            @RequestParam("price") String price,
            @RequestParam("category") String category) {

        String uploadDir = "/Users/chodaekyung/Desktop";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // 경로가 없으면 생성
        }

        try {
            if (file.isEmpty()) {
                throw new IOException("파일이 비어 있습니다."); // 예외 발생
            }
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File targetFile = new File(uploadDir, fileName);
            file.transferTo(targetFile);

            itemService.saveItem(item,"/Users/chodaekyung/Desktop/" + fileName, detail, seller, price,category);
            return ResponseEntity.ok("파일 업로드 성공"); // 성공 메시지 반환
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 오류: " + e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteItemListById(@RequestBody ItemDeleteRequestDTO itemDeleteRequestDTO) {
        Long id = itemDeleteRequestDTO.getId();
        itemService.deleteItemListById(id);
        System.out.println("Now Id :: " + id);
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }

    @PutMapping("/likes")
    public ResponseEntity<Void> itemLikes(@RequestBody LikesRequestDTO likesRequestDTO) {
        System.out.println("get Id :: " + likesRequestDTO.getId());
        System.out.println("get version :: " + likesRequestDTO.getVersion());
        itemService.itemLikes(likesRequestDTO.getId(),likesRequestDTO.getVersion());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/likesCount")
    public ResponseEntity<Void> itemLikesCount(@RequestBody LikesCountRequestDTO likesCountRequestDTO) {
        System.out.println(likesCountRequestDTO.getItemId());
        System.out.println(likesCountRequestDTO.getUserId());
        itemService.likesCount(likesCountRequestDTO.getUserId(), likesCountRequestDTO.getItemId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchResponseDTO>> searchItem(
            @RequestParam("search") String search
    ) {
        // 검색어를 이용하여 Item 리스트를 가져옴
        List<SearchResponseDTO> items = itemService.searchItem(search);

        // 검색 결과를 ResponseEntity로 반환
        return ResponseEntity.ok(items);
    }
}
