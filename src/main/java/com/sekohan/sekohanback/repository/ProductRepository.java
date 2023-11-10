package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p WHERE p.categoryEntity.catId = :catId")
    Page<ProductEntity> findByCategoryList(Pageable pageable, @Param("catId") Long catId);

    @Query("SELECT p FROM ProductEntity p ORDER BY p.proPrice ASC")
    Page<ProductEntity> findpricASCall(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p ORDER BY p.proPrice DESC")
    Page<ProductEntity> findpricDESCall(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p ORDER BY p.proPrice DESC")
    Page<ProductEntity> findtimeASCall(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p ORDER BY p.proPrice DESC")
    Page<ProductEntity> findtimeDESCall(Pageable pageable);
}