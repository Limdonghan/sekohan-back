package com.sekohan.sekohanback.service.user.signin;

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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SIgnInServiceImpl implements SignInService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;  //사용하기 위해 빈 설정

    private final RedisTemplate<String, String> redisTemplate;

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

    /* Refresh token 으로 Access Token 재생성 */
    @Override
    public JsonWebTokenResponseDTO refresh(String refreshToken) {
        //Refresh Token 검증
        Jws<Claims> claims = jwtService.getClaims(jwtService.extractToken(refreshToken));
        if(jwtService.isWrongType(claims, JwtType.REFRESH)) {
            throw TokenTypeException.EXCEPTION;
        }
        /* Redis에 저장된 RefreshToken 값을 가져와서
        * body에 있는 RefreshToken과 값을 비교 */
        String redisRefreshToken = redisTemplate.opsForValue().get(claims.getBody().getSubject());

        if(!redisRefreshToken.equals(refreshToken)){
             throw TokenTypeException.EXCEPTION; //RefreshToken가 일치하지 않으면 오류 던지기
        }

        //AccessToken 재생성
        String accessToken = jwtService.generateAccessToken(claims.getBody().getSubject());

        return JsonWebTokenResponseDTO.builder()
                .accessToken(accessToken).build();
    }
}