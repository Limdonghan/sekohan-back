package com.sekohan.sekohanback.service.wishlist;

import com.sekohan.sekohanback.dto.WishListDTO;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.WishListEntity;
import com.sekohan.sekohanback.repository.WishListrepository;
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

    private final WishListrepository wishListrepository;
    @Override
    public WishListEntity uploadWishList(ProductEntity productEntity, UserEntity userEntity) {
        WishListEntity wishListEntity = WishListEntity.builder().productEntity(productEntity).userEntity(userEntity)
        .localDateTime(LocalDateTime.now()).build();

        WishListEntity savewishList = wishListrepository.save(wishListEntity);

        return savewishList;
    }

    @Override
    public List<WishListDTO> getuserWishList(long uId) {
        List<WishListEntity> wishlist = wishListrepository.findbyuIdList(uId);
        return wishlist.stream()
                .map(this::wishListDTO)
                .collect(Collectors.toList());
    }


    public WishListDTO wishListDTO(WishListEntity wishList){
        WishListDTO wishListDTO = new WishListDTO();
        wishListDTO.setWishListId(wishList.getWishListId());
        wishListDTO.setLocalDateTime(wishList.getLocalDateTime());
        wishListDTO.setProductId(wishList.getProductEntity().getProductId());
        wishListDTO.setUId(wishList.getUserEntity().getUId());

        return wishListDTO;
    }
}
