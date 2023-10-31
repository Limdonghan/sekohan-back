package com.sekohan.sekohanback.service;

import com.sekohan.sekohanback.dto.WishListDTO;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.WishListEntity;
import com.sekohan.sekohanback.repository.WishiListrepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j  //log.info("sdfsdf")  //log.info
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishiListrepository wishiListrepository;
    @Override
    public WishListEntity uploadWishList(ProductEntity productEntity, UserEntity userEntity) {
        WishListEntity wishListEntity = new WishListEntity();
        wishListEntity.setProductId(productEntity);
        wishListEntity.setUId(userEntity);
        wishListEntity.setLocalDateTime(LocalDateTime.now());

        WishListEntity savewishList = wishiListrepository.save(wishListEntity);

        return savewishList;
    }

    @Override
    public List<WishListDTO> getuserWishList(long uId) {
        List<WishListEntity> wishlist = wishiListrepository.findbyuId(uId);
        return wishlist.stream()
                .map(this::wishListDTO)
                .collect(Collectors.toList());
    }


    public WishListDTO wishListDTO(WishListEntity wishList){
        WishListDTO wishListDTO = new WishListDTO();
        wishListDTO.setLocalDateTime(wishList.getLocalDateTime());
        wishListDTO.setProductId(wishList.getProductId().getProductId());
        wishListDTO.setUId(wishList.getUId().getUId());

        return wishListDTO;
    }
}
