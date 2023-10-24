package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<UserEntity, Long> { //Entity 클래스명과 ID의 타입을 명시


}
