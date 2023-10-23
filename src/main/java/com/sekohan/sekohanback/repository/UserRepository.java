package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> { //<Entity 클래스명, ID의 타입>을 명시

    boolean existsByLogin(String s1);  //사용자 로그인아이디 유효성체크

    boolean existsByNickname(String s2);  //사용자 닉네임 유효성체크
}
