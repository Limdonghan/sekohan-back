package com.sekohan.sekohanback.security.repository;

import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.security.principal.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserSecurityRepositoryImpl implements UserSecurityRepository{


    @Override
    public UserEntity getUser() {

        return ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserEntity();
    }
}
