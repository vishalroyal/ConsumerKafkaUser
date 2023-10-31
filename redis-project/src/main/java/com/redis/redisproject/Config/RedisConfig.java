package com.redis.redisproject.Config;

import com.redis.redisproject.Model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    public JedisConnectionFactory getConnection(){
        JedisConnectionFactory connectionFactory=new JedisConnectionFactory();
        return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, Person> redisTemplate(){
        RedisTemplate<String,Person> redisTemplate=new RedisTemplate<String,Person>();
        redisTemplate.setConnectionFactory(getConnection());
        return redisTemplate;

    }

}
