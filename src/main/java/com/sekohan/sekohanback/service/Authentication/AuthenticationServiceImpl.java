package com.sekohan.sekohanback.service.authentication;

import com.sekohan.sekohanback.dto.jwt.JsonWebTokenResponseDTO;
import com.sekohan.sekohanback.dto.user.sign.UserSignInDTO;
import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.exception.TokenTypeException;
import com.sekohan.sekohanback.jwt.enums.JwtType;
import com.sekohan.sekohanback.jwt.service.JwtService;
import com.sekohan.sekohanback.security.principal.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;  //사용하기 위해 빈 설정

    @Override
    public JsonWebTokenResponseDTO auth(UserSignInDTO authRequset) {
        log.info("UserPasswordAuthenticationToken 발급");  // 사용자의 ID,PW로 인증토큰 발급
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequset.getLogin(), authRequset.getPassword()));
        log.info("Authenticate정보 : {}",authenticate);
        UserEntity user = ((UserPrincipal) authenticate.getPrincipal()).getUserEntity();
        log.info("User정보 : {}",user);
        return JsonWebTokenResponseDTO.builder()
                .accessToken(jwtService.generateAccessToken(user.getLogin()))   //로그인 정보를 가지고 액세트 토큰 생성
                .refreshToken(jwtService.generateRefreshToken(user.getLogin()))  //로그인 정보를 가지고 리프레쉬 토큰 생성
                .build();
    }

    @Override
    public JsonWebTokenResponseDTO refresh(String token) {
        log.info("--------JWT 응답--------");
        Jws<Claims> claims = jwtService.getClaims(jwtService.extractToken(token));
        log.info("JWT<Claims> : {}",claims);
        if(jwtService.isWrongType(claims, JwtType.REFRESH)) {
            throw TokenTypeException.EXCEPTION;
        }
        return JsonWebTokenResponseDTO.builder()
                .accessToken(jwtService.generateAccessToken(claims.getBody().getSubject())).build();
    }
}