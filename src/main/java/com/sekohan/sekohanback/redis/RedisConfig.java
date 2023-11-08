package com.sekohan.sekohanback.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    /* Springboot 프로젝트에서 redis를 사용하기 위한 설정
       Lettuce Redis Client 사용 → RedisTemplate 의 메서드로 Redis 서버에 명령을 수행할 수 있음
       application.yml의 환경변수를 @Value 어노테이션을 사용해 변수에 주입 */

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(redisHost,redisPort);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        /* redisTemplate를 받아와서 set, get, delete를 사용*/
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        /*setKeySerializer, setValueSerializer 설정
         redis-cli을 통해 직접 데이터를 조회 시 알아볼 수 없는 형태로 출력되는 것을 방지 */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }


}
