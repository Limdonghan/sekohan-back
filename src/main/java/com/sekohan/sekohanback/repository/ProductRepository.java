package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p WHERE p.categoryEntity.catId = :catId")
    Page<ProductEntity> findByCategoryList(Pageable pageable, @Param("catId") Long catId);

    @Transactional
    @Modifying
    @Query("UPDATE ProductEntity p SET p.proView = p.proView + 1 WHERE p.productId = :productId")
    int viewup(@Param("productId") Long productId);
}