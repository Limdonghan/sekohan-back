package com.sekohan.sekohanback.jwt.filter;

import com.sekohan.sekohanback.jwt.service.JwtService;
import com.sekohan.sekohanback.redis.RedisService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final RedisService redisService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

        /* Http 요청이 오면 인증필터로 먼저 오고 
        토큰을 인증 하기위해 jwtService.extractTokenFromRequest()로 이동  
        Bearer을 뺀 토큰을 token에 저장 */
        String token = jwtService.extractTokenFromRequest(request);


        if (token != null) {
            /* 토큰이 널이 아니라면 jwtService.getAuthentication() 토큰을 매개변수로 받고 이동
            * 정상 토큰이면 SecurityContext에 저장*/
            /* 로그아웃 요청시 이미 로그아웃 처리된 블랙 리스트된 JWT 토큰인지를 검증 */
            if (redisService.hasKeyBlackList(token)) {
                throw new RuntimeException("블랙리스트 토큰");
            }
            SecurityContextHolder.getContext().setAuthentication(jwtService.getAuthentication(token));
        }


        filterChain.doFilter(request, response);
    }
}
