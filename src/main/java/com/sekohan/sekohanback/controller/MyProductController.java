package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.CategoryEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.service.myproduct.MyProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/myproducts")
@RequiredArgsConstructor
public class MyProductController {

    private final MyProductService MyproductService;

    @GetMapping("/list/{uId}")
    public List<proImageDTO> getuserproduct(@PathVariable long uId) {
        return MyproductService.getuserproduct(uId);
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
        return MyproductService.updateProduct(ProductId, proName, proPrice, proInfo,userEntity, categoryEntity ,status, files);
    }
    //상품 업데이트 URL

}
