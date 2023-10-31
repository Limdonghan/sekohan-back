package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.WishListDTO;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.WishListEntity;
import com.sekohan.sekohanback.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/add")
    public WishListEntity uploadWishList(@RequestParam("productId") long productId,
                                        @RequestParam("userId") long userId) {
        ProductEntity productEntity = new ProductEntity(productId);
        productEntity.setProductId(productId);
        UserEntity userEntity = new UserEntity(userId);
        userEntity.setUId(userId);
        return wishListService.uploadWishList(productEntity, userEntity);
    }

    @GetMapping("/list/{uId}")
    public List<WishListDTO> getuserWishList(@PathVariable long uId){
        return wishListService.getuserWishList(uId);
    }
}
