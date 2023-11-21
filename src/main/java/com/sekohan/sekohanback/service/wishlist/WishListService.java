package com.sekohan.sekohanback.service.wishlist;

import com.sekohan.sekohanback.dto.WishListDTO;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.WishListEntity;

import java.util.List;

public interface WishListService {

    WishListEntity WishListAdd(ProductEntity productEntity, UserEntity userEntity);

    List<WishListDTO> UserWishList(long uId);

    int checkWish(long uId, long proId);

    void DeleteWishList(long wishId);
}
