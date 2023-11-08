package com.sekohan.sekohanback.security.service;

import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.exception.NotFoundUserException;
import com.sekohan.sekohanback.repository.UserRepository;
import com.sekohan.sekohanback.security.principal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ClubUserDetailsService 사용자 아이디 :" + username);

        log.info("사용자 존재 유무 확인 in DB");
        UserEntity userEntity = userRepository.findByLogin(username).orElseThrow(() -> NotFoundUserException.EXCEPTION);

        log.info("CustomUserDetailsServiceImplLog {}",userEntity);  //사용자의 아이디를 가져옴
        UserPrincipal userPrincipal = new UserPrincipal(userEntity);
        
        log.info("userPrincipal 이동");
        return userPrincipal;
        //return UserPrincipal.create(userEntity);  //사용자의 아이디를 UserPrincipal.create로 리턴

    }
}
