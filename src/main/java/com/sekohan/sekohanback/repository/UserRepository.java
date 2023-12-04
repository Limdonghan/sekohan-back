package com.sekohan.sekohanback.repository;

import com.sekohan.sekohanback.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> { //<Entity 클래스명, ID의 타입>을 명시

    @EntityGraph(attributePaths = {"userRole"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from UserEntity m where m.login=:login")
    Optional<UserEntity> findByLogin(@Param("login") String login);  //UserEntity 조회 시 로그인아이디를 기준으로 조회

    // 회원 프로필 사진 변경 때 씀
    @EntityGraph(attributePaths = {"userRole"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from UserEntity m where m.login=:login")
    UserEntity findByLogin2(@Param("login") String login);  //UserEntity 조회 시 아이디를 기준으로 조회


    @Query("select m from UserEntity m where m.name=:name and m.email=:email ")
    Optional<UserEntity> findByLogin(@Param("email") String email,
                                     @Param("name") String name);
    @Query("select m from UserEntity m where m.login=:login and m.name=:name and m.email=:email")
    Optional<UserEntity> findByPassword(@Param("email") String email,
                                        @Param("login") String login,
                                        @Param("name") String name);

    boolean existsByLogin(String s1);  //사용자 아이디 유효성 체크
    boolean existsByNickname(String s2);  //사용자 닉네임 유효성 체크
    boolean existsByEmail (String s3);  //인증번호 전송전에 유효한 이메일인지 체크
    @Query("SELECT m from UserEntity m")
    List<UserEntity> findAll();



    // test
    @Query("select m from UserEntity m where m.uId=:uId")
    UserEntity findByUId(@Param("uId")long uId);

}
