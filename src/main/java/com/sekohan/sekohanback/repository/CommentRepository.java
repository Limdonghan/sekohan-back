package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.CommentEntity;
import com.sekohan.sekohanback.entity.img.ProImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("SELECT p FROM CommentEntity p WHERE p.productId.productId = :productId")
    List<CommentEntity> findByProductId(@Param("productId") Long productId);

}
