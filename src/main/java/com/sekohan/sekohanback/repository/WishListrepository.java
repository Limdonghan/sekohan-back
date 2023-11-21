package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListrepository extends JpaRepository<WishListEntity, Long > {

    @Query("SELECT p FROM WishListEntity p WHERE p.userEntity.uId = :uId")
    List<WishListEntity> findbyuIdList(@Param("uId") Long uId);

    Optional<WishListEntity> findByProductEntityAndUserEntity(ProductEntity productEntity, UserEntity userEntity);

    @Query("SELECT p FROM WishListEntity p WHERE p.productEntity.productId = :productId")
    List<WishListEntity> getproId(@Param("productId") Long productId);
}
