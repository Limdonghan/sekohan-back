package com.sekohan.sekohanback.security.repository;

import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.security.principal.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserSecurityRepositoryImpl implements UserSecurityRepository{


    @Override
    public UserEntity getUser() {
        log.info("사용자 권한 조회");
        //UserEntity에서 아이디와 권한을 조회
        UserEntity userEntity = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserEntity();
        log.info("사용자 권한 검사 완료");
        return userEntity;
    }
}
