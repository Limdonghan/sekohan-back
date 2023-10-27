package com.sekohan.sekohanback.jwt.filter;

import com.sekohan.sekohanback.jwt.service.JwtService;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("-----Jwt인증필터-----");
        String token = jwtService.extractTokenFromRequest(request);
        log.info("token : {}",token);
        if (token != null) {
            SecurityContextHolder.getContext().setAuthentication(jwtService.getAuthentication(token));
        }
        log.info("token2 : {}",token);
        filterChain.doFilter(request, response);
    }
}
