package com.sekohan.sekohanback.controller;

import com.sekohan.sekohanback.dto.WishListDTO;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.service.wishlist.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/add")
    public String WishListAdd(@RequestParam("productId") long productId,
                              @RequestParam("userId") long userId) {
        ProductEntity productEntity = ProductEntity.builder().productId(productId).build();
        UserEntity userEntity = UserEntity.builder().uId(userId).build();
        return wishListService.WishListAdd(productEntity, userEntity);
    }
    //찜목록 추가 URL

    @GetMapping("/list/{uId}")
    public List<WishListDTO> UserWishList(@PathVariable long uId) {
        return wishListService.UserWishList(uId);
    }
    //유저 찜목록 URL

    @DeleteMapping("/{wishId}")
    public void DeleteWishList(@PathVariable long wishId) {
        wishListService.WishListDelete(wishId);
    }


}
