package com.sekohan.sekohanback.service.user.signout;

import com.sekohan.sekohanback.jwt.service.JwtService;
import com.sekohan.sekohanback.redis.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignOutServiceImpl implements SignOutService{
    private final JwtService jwtService;
    private final RedisService redisService;

    @Override
    @Transactional
    public void logout(String accessToken){
        Jws<Claims> claims = jwtService.getClaims(accessToken);

        Long expiration = jwtService.getExpiration(accessToken);

        //Refresh Token 삭제
        redisService.deleteValues(claims.getBody().getSubject());

        //Access Token 블랙리스트 등록
        redisService.setValuesWithTimeout(accessToken,"logout",expiration,TimeUnit.MILLISECONDS);


    }

}
