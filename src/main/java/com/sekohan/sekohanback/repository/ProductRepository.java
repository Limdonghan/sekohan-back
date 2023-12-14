package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductRepository extends
        JpaRepository<ProductEntity, Long>,
        QuerydslPredicateExecutor<ProductEntity> {

}
