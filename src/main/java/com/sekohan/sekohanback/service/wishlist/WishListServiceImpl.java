package com.sekohan.sekohanback.service.wishlist;

import com.sekohan.sekohanback.dto.WishListDTO;
import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.WishListEntity;
import com.sekohan.sekohanback.entity.img.ProImageEntity;
import com.sekohan.sekohanback.repository.ProImageRepository;
import com.sekohan.sekohanback.repository.WishListrepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j  //log.info("sdfsdf")  //log.info
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishListrepository wishListrepository;
    private final ProImageRepository proImageRepository;
    @Override
    public WishListEntity WishListAdd(ProductEntity productEntity, UserEntity userEntity) {

        if (wishListrepository.findByProductEntityAndUserEntity(productEntity, userEntity).isPresent()) {
            log.info("중복값");
            return null;
        }

        try {
            WishListEntity wishListEntity = WishListEntity.builder()
                    .productEntity(productEntity)
                    .userEntity(userEntity)
                    .localDateTime(LocalDateTime.now())
                    .build();

            return wishListrepository.save(wishListEntity);
        } catch (DataIntegrityViolationException e) {
            log.info("중복값");
            return null;
        }
    }

    @Override
    public List<WishListDTO> UserWishList(long uId) {
        List<WishListEntity> wishlist = wishListrepository.findbyuIdList(uId);
        return wishlist.stream()
                .map(this::wishListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void WishListDelete(long wishId) {
        wishListrepository.deleteById(wishId);
    }

    public WishListDTO wishListDTO(WishListEntity wishList){
        WishListDTO wishListDTO = new WishListDTO();
        wishListDTO.setWishListId(wishList.getWishListId());
        wishListDTO.setLocalDateTime(wishList.getLocalDateTime());
        wishListDTO.setProductId(wishList.getProductEntity().getProductId());
        wishListDTO.setUId(wishList.getUserEntity().getUId());
        wishListDTO.setTitle(wishList.getProductEntity().getProName());
        wishListDTO.setInfo(wishList.getProductEntity().getProInfo());
        wishListDTO.setPrice(wishList.getProductEntity().getProPrice());

        List<ProImageEntity> proImageEntities = proImageRepository.findproid(wishList.getProductEntity().getProductId());
                if (!proImageEntities.isEmpty()) {
                    ProImageEntity proImageEntity = proImageEntities.get(0); // 가장 오래된 항목 선택
                    wishListDTO.setPath(proImageEntity.getPath());
                }

        return wishListDTO;
    }
}
