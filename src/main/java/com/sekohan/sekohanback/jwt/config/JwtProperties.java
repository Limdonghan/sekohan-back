package com.sekohan.sekohanback.jwt.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.security.jwt")  //application.yml 경로 저장
public class JwtProperties {
    private String secretKey;  //JWT 비밀키를 저장
    private long expiration;  //JWT 만료시간을 저장
    private long refreshExpiration;  //Refresh Token의 만료시간 저장
}
