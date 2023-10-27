package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> { //<Entity 클래스명, ID의 타입>을 명시

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from UserEntity m where m.login=:login")
    Optional<UserEntity> findByLogin(@Param("login") String login);  //UserEntity 조회 시 로그인아이디를 기준으로 조회


    boolean existsByLogin(String s1);  //사용자 로그인아이디 유효성체크
    boolean existsByNickname(String s2);  //사용자 닉네임 유효성체크
}
