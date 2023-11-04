package com.sekohan.sekohanback.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter { //OncePerRequestFilter : 요청 당 한 번만 적용되도록 도와주는 필터

    @Override  //요청 할때마다 들어옴
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("-----Jwt예외필터-----");
        try {
            log.info("-----try-----");
            log.info("request : {}",request);
            log.info("response : {}",response);
            log.info("filterChain : {}",filterChain);
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            log.info("-----catch1-----");
            log.info(e.getMessage());
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e);
        } catch (ServletException e) {
            log.info("-----catch2-----");
            log.info(e.getMessage());
            setErrorResponse(HttpStatus.BAD_REQUEST, response, e);
        }

    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) throws IOException {
        log.info("-----setErrorResponse-----");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status.value());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        map.put("status", String.valueOf(status.value()));
        map.put("message", ex.getMessage());
        response.getWriter().write(mapper.writeValueAsString(map));
    }
}
