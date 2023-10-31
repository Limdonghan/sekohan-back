package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListrepository extends JpaRepository<WishListEntity, Long > {

    @Query("SELECT p FROM WishListEntity p WHERE p.uId.uId = :uId")
    List<WishListEntity> findbyuIdList(@Param("uId") Long uId);

}
