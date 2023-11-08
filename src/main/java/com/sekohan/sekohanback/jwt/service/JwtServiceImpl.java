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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor //생성자를 자동으로 생성해주는 어노테이션
public class JwtServiceImpl implements JwtService{

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;

    /* jwt 토큰을 구문 분석하고 Claims을 반환, 토큰 유효성 확인  , Access Token 검증 */
    @Override
    public Jws<Claims> getClaims(final String token) {
        try {
            log.info("Jws<Claims> 검증 ");

            //시발 만료가 되면 재발급 하라고

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token);
            log.info("claimsJws : {}",claimsJws);

            return claimsJws;
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("만료된 토큰");
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("지원되지 않는 토큰");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 토큰");
        }
    }


    /* 유저의 로그인ID 값을 가져와 Access Token 생성 */
    @Override
    public String generateAccessToken(final String loginID) {
        log.info("--------generateAccessToken--------");
        String accessToken = Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)   //헤더 메개변수 설정
                .setSubject(loginID)  //내용
                .setIssuedAt(new Date(System.currentTimeMillis())) //발급시간
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration())) //만료시간
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) //비밀키로 서명
                .compact();//생성
        log.info("accessToken : {}",accessToken);
        return accessToken;
    }

    /* Refresh Token 생성 */
    @Override
    public String generateRefreshToken(final String loginID) {
        log.info("--------generateRefreshToken--------");
        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.REFRESH)
                .setSubject(loginID)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();

        //redis에 저장
        redisTemplate.opsForValue().set(
                loginID,
                refreshToken,
                jwtProperties.getRefreshExpiration(),
                TimeUnit.MILLISECONDS
        );
        log.info("refreshToken : {}", refreshToken);
        return refreshToken;

    }

    /* 토큰으로 부터 클레임을 만들고, 이를 통해 User 객체 생성해 Authentication 객체 반환 */
    @Override
    public Authentication getAuthentication(final String token) {  //Access Token의 구문 분석된 Claims을 기반으로 Authentication개체를 생성
        log.info("--------getAuthentication들어왔어요--------");

        /* 토큰 유효성 검증(getClaims()로 이동 후)을 먼저 하고 Jws<Claims>타입의 claims 생성*/
        final Jws<Claims> claims = getClaims(token);

        if (isWrongType(claims, JwtType.ACCESS)) {
            throw TokenTypeException.EXCEPTION;
        }

        UserEntity userEntity = userRepository.findByLogin(claims.getBody().getSubject()).orElseThrow(() -> NotFoundUserException.EXCEPTION);
       //UserPrincipal userPrincipal = UserPrincipal.create(userEntity);
        UserPrincipal userPrincipal = new UserPrincipal(userEntity);

        log.info("--------getAuthentication처리완료--------");
        return new UsernamePasswordAuthenticationToken(userPrincipal,null,userPrincipal.getAuthorities());
    }

    /* Http 요청의 authorization를 헤더로 가져와서 헤더에서
        extractToken메서드로 리턴
        extractToken으로 리턴 */
    @Override
    public String extractTokenFromRequest(HttpServletRequest request) {
        log.info("extractTokenFromRequest검사");
        log.info("extractTokenFromRequest_request : {}",request);

        String extractToken = extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        log.info("extractToken: {}", extractToken);

        return extractToken;
    }

    /* token을 매개변수로 받음 
    토큰이 비어있는지 확인 && Bearer로 시작하면 Bearer를 제거 후 리턴 */
    @Override
    public String extractToken(final String token) {
        if (StringUtils.hasText(token)
                && token.startsWith("Bearer ")) {
            log.info("extractToken 검사");
            return token.substring(7);
        }
        return token;
    }

    /* JWT의 헤더에서 Header.JWT_TYPE을 검사하고, 이 값이 jwtType과 일치하지 않으면 false을 반환, 일치하면 true */
    @Override
    public boolean isWrongType(
            final Jws<Claims> claims,
            final JwtType jwtType
    ) {  //Claims에서 JWT유형이 jwtType과 일치하는지 확인
        return !(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()));
    }
}
