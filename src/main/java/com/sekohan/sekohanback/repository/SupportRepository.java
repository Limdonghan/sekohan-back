package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.ProductEntity;
import com.sekohan.sekohanback.entity.ServiceEntity;
import com.sekohan.sekohanback.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportRepository extends JpaRepository<ServiceEntity, Long > {
    Optional<ServiceEntity> findByProductEntityAndUserEntity(ProductEntity productEntity, UserEntity userEntity);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity p SET p.report = p.report + 1 WHERE p.uId = :uId")
    int reportup(@Param("uId") Long uId);

    @Query("SELECT p FROM ServiceEntity p WHERE p.productEntity.productId = :productId")
    List<ServiceEntity> getproid(long productId);
}
