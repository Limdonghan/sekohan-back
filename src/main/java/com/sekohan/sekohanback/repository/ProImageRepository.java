package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.img.ProImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProImageRepository extends JpaRepository<ProImageEntity, Long> {


}
