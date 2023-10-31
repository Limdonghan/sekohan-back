package com.sekohan.sekohanback.service;

import com.sekohan.sekohanback.dto.WishListDTO;
import com.sekohan.sekohanback.dto.proImageDTO;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.WishListEntity;

import java.util.List;

public interface WishListService {

    WishListEntity uploadWishList(ProductEntity productEntity, UserEntity userEntity);

    List<WishListDTO> getuserWishList(long uId);
}
