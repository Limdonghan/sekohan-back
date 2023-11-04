package com.sekohan.sekohanback.jwt.service;

import com.sekohan.sekohanback.entity.UserEntity;
import com.sekohan.sekohanback.exception.NotFoundUserException;
import com.sekohan.sekohanback.exception.TokenTypeException;
import com.sekohan.sekohanback.jwt.config.JwtProperties;
import com.sekohan.sekohanback.jwt.enums.JwtType;
import com.sekohan.sekohanback.repository.UserRepository;
import com.sekohan.sekohanback.security.principal.UserPrincipal;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor //생성자를 자동으로 생성해주는 어노테이션
public class JwtServiceImpl implements JwtService{

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;

    @Override
    public Jws<Claims> getClaims(final String token) {  //jwt 토큰을 구문 분석하고 Claims을 반환, 토큰 유효성 확인
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("만료된 토큰");
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("지원되지 않는 토큰");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 토큰");
        }
    }


    @Override
    public String generateAccessToken(final String loginID) {  //loginID에 대한 AccessToken 생성
        log.info("--------generateAccessToken--------");
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)
                .setSubject(loginID)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(final String loginID) {  //loginID에 대한 RefreshToken 생성
        log.info("--------generateRefreshToken--------");
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.REFRESH)
                .setSubject(loginID)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getRefreshExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    @Override
    public Authentication getAuthentication(final String token) {  //Access Token의 구문 분석된 Claims을 기반으로 Authentication개체를 생성
        log.info("--------getAuthentication들어왔어요--------");
        final Jws<Claims> claims = getClaims(token);
        if (isWrongType(claims, JwtType.ACCESS)) {
            throw TokenTypeException.EXCEPTION;
        }
        UserEntity userEntity = userRepository.findByLogin(claims.getBody().getSubject()).orElseThrow(() -> NotFoundUserException.EXCEPTION);
        //UserPrincipal userPrincipal = UserPrincipal.create(userEntity);
        UserPrincipal userPrincipal = new UserPrincipal(userEntity);

        log.info("--------getAuthentication처리완료--------");
        return new UsernamePasswordAuthenticationToken(userPrincipal.getUserEntity(),null,userPrincipal.getAuthorities());
       // return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }

    @Override
    public String extractTokenFromRequest(HttpServletRequest request) {  //Http 요청의 authorization헤더에서 JWT토큰을 추출
        log.info("extractTokenFromRequest_request : {}",request);
        return extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    @Override
    public String extractToken(final String token) {  //JWT 토큰에서 Bearer 접두사를 제거
        if (StringUtils.hasText(token)
                && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    @Override
    public boolean isWrongType(
            final Jws<Claims> claims,
            final JwtType jwtType
    ) {  //Claims에서 JWT유형이 jwtType과 일치하는지 확인
        return !(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()));
    }
}
