package com.sekohan.sekohanback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean  //passwordEncoder라는 빈 정의
    PasswordEncoder passwordEncoder() {  //PasswordEncoder인터페이스는 사용자 암호를 인코딩하는데 사용
        return new BCryptPasswordEncoder();
    }

}
