package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.service.myproduct.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyProductController {

    private final MypageService myproductService;

    @GetMapping("/list/{uId}")
    public List<proImageDTO> UserProList(@PathVariable long uId) {
        return myproductService.UserProList(uId);
    }

    @GetMapping("/soldout/{uId}")
    public List<proImageDTO> UserProSoldoutList(@PathVariable long uId) {
        return myproductService.UserProSoldoutList(uId);
    }
    //내 상품 목록 URL

    @PutMapping("/update/{ProductId}")
    public ProductEntity updateProduct(@PathVariable long ProductId,
                                       @RequestParam("proName") String proName,
                                       @RequestParam("proPrice") int proPrice,
                                       @RequestParam("proInfo") String proInfo,
                                       @RequestParam("status") byte status,
                                       @RequestParam("categoryId") long categoryId,
                                       @RequestParam("userId") long userId,
                                       @RequestPart("files") List<MultipartFile> files) {
        CategoryEntity categoryEntity = CategoryEntity.builder().catId(categoryId).build();
        UserEntity userEntity = UserEntity.builder().uId(userId).build();
        return myproductService.Productupdate(ProductId, proName, proPrice, proInfo,userEntity, categoryEntity ,status, files);
    }
    //상품 업데이트 URL

    @PostMapping("/upload")
    public ProductEntity ProductUpload(@RequestParam("proName") String proName,
                                       @RequestParam("proPrice") int proPrice,
                                       @RequestParam("proInfo") String proInfo,
                                       @RequestParam("categoryId") long categoryId,
                                       @RequestParam("userId") long userId,
                                       @RequestPart("files") List<MultipartFile> files) {
        CategoryEntity categoryEntity = CategoryEntity.builder().catId(categoryId).build();
        UserEntity userEntity = UserEntity.builder().uId(userId).build();
        return myproductService.ProductUpload(proName, proPrice, proInfo, categoryEntity, userEntity, files);
    }
    //상품 업로드 URL

}
