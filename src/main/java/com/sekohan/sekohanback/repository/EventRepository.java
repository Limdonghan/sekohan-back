package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.EventEntity;
import com.sekohan.sekohanback.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface EventRepository extends
        JpaRepository<EventEntity,Long>,
        QuerydslPredicateExecutor<EventEntity> {


}
