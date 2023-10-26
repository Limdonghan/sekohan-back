package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.img.ProImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProImageRepository extends JpaRepository<ProImageEntity, Long> {

    @Query("SELECT p FROM ProImageEntity p WHERE p.productId.productId = :productId")
    List<ProImageEntity> getImagesByProductId(@Param("productId") Long productId);


}
