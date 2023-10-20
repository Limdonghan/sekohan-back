package com.sekohan.sekohanback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SekohanBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SekohanBackApplication.class, args);
    }

}
