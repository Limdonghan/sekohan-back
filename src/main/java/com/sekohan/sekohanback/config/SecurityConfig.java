package com.sekohan.sekohanback.config;

import com.sekohan.sekohanback.jwt.filter.JwtAuthenticationFilter;
import com.sekohan.sekohanback.jwt.filter.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig  {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

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
    log.info("Security Config");
        http
                .csrf(AbstractHttpConfigurer::disable)  //csrf 토큰 비활성화
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/api/user/**").permitAll()   //모든 사용자에게 이 경로는 허락
                                .requestMatchers("/api/help/**").permitAll()  //모든 사용자에게 이 경로는 허락
                                .requestMatchers("/api/email/**").permitAll()
                                .requestMatchers("/api/products/**").permitAll()
                                .requestMatchers("/api/my/**").hasRole("USER")  //본인만 사용가능한 경로
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")  //관리자만 사용가능한 경로
                                .anyRequest().authenticated()
                )
                /* addFilterBefore : 필터처리를 하기전에 필요한 것 */
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //받은 토큰을 풀어줘야함
                .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);
                //.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.isCustomLoginPage());
        return http.build();
    }

}
