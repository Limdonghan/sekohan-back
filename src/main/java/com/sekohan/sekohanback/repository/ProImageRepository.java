package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.img.ProImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProImageRepository extends JpaRepository<ProImageEntity, Long> {

    @Query("SELECT p FROM ProImageEntity p WHERE p.productEntity.productId = :productId")
    List<ProImageEntity> getPro_imgId(@Param("productId") Long productId);

    @Query("SELECT p FROM ProImageEntity p WHERE p.productEntity.categoryEntity.catId = :catId")
    List<ProImageEntity> findByCategoryList(@Param("catId") Long catId);

    @Query("SELECT p FROM ProImageEntity p WHERE p.productEntity.userEntity.uId = :uId")
    List<ProImageEntity> findByuId(@Param("uId") Long uId);

}
