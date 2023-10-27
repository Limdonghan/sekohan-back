package com.sekohan.sekohanback.security.principal;

import com.sekohan.sekohanback.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@ToString
@Getter
@Slf4j
public class UserPrincipal implements UserDetails {  //로그인된 유저의 계정 정보
    private final UserEntity userEntity;  //사용자 정보
    private Collection<? extends GrantedAuthority> authorities;  //사용자의 권한 목록

    public static UserPrincipal create(UserEntity userEntity){
        log.info("UserPrincipalLog {}",userEntity);
        return new UserPrincipal(
                userEntity,
                userEntity.getRoleSet().stream().map(
                        userRole -> new SimpleGrantedAuthority(userRole.getKey())).collect(Collectors.toList()));
    }



    public UserPrincipal(UserEntity userEntity, Collection<? extends  GrantedAuthority> authorities){
        this.userEntity =userEntity;
        this.authorities=authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  //계정의 권한 목록을 리턴

        log.info("사용자의 권한 : {}",authorities);
        return authorities;
    }

    @Override
    public String getPassword() {  //계정의 비밀번호를 리턴

        log.info("사용자의 비밀번호 : {}",userEntity.getPassword());
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {  ///계정의 로그인아이디을 리턴
        log.info("사용자의 로그인아이디 : {}",userEntity.getLogin());
        return userEntity.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {  //계정의 만료 여부 리턴
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  //계정의 잠김 여부 리턴
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  //비밀번호 만료 여부 리턴
        return true;
    }

    @Override
    public boolean isEnabled() {  //계정의 활성화 여부 리턴
        return true;
    }
}
