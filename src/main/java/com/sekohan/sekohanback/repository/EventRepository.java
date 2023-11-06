package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity,Long> {


}
