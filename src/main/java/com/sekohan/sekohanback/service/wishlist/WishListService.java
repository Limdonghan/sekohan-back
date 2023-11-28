package com.sekohan.sekohanback.service.wishlist;

import com.sekohan.sekohanback.dto.WishListDTO;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;

import java.util.List;

public interface WishListService {

    String WishListAdd(ProductEntity productEntity, UserEntity userEntity);

    List<WishListDTO> UserWishList(long uId);


    void WishListDelete(long wishId);
}
