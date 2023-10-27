package com.sekohan.sekohanback.jwt.service;

import com.sekohan.sekohanback.jwt.enums.JwtType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtService {


    Jws<Claims> getClaims(String token);

    String generateAccessToken(final String loginID);
    String generateRefreshToken(final String loginID);

    Authentication getAuthentication(String token);

    String extractTokenFromRequest(HttpServletRequest request);

    String extractToken(String token);

    boolean isWrongType(
            Jws<Claims> claims,
            JwtType jwtType
    );
}
