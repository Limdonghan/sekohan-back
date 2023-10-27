package com.sekohan.sekohanback.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    @Bean  //
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean  //passwordEncoder라는 빈 정의
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  //csrf 토큰 비활성화
                //.cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/api/user/**").permitAll()   //모든 사용자에게 이 경로는 허락
                                .requestMatchers("/api/my/**").hasRole("USER")  //본인만 사용가능한 경로
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")  //관리자만 사용가능한 경로
                                .anyRequest().authenticated()  //인증이 되어야 한다
                )
                //addFilterBefore : 필터처리를 하기전에 필요한 것
               // .addFilterBefore(new JwtExceptionFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)  //받은 토큰을 풀어줘야함
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.isCustomLoginPage());
        return http.build();
    }
}
