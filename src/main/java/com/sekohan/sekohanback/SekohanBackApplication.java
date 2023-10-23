package com.sekohan.sekohanback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})  // (exclude = {SecurityAutoConfiguration.class}) 로그인 화면 뜨지 않게 하는 방법
@EnableJpaAuditing
public class SekohanBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SekohanBackApplication.class, args);
    }

}
