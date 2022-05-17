package com.fet.telemedicine.backend.chat.config;

import java.time.Duration;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fet.telemedicine.backend.chat.util.JsonUtil;

@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    
    public static final String CACHE_KEY_GENERATOR = "cacheKeyGenerator";

    public static final String REDIS_KEY_DATABASE = "redis";

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
	RedisSerializer<Object> serializer = redisSerializer();
	RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	redisTemplate.setConnectionFactory(redisConnectionFactory);
	redisTemplate.setKeySerializer(new StringRedisSerializer());
	redisTemplate.setValueSerializer(serializer);
	redisTemplate.setHashKeySerializer(new StringRedisSerializer());
	redisTemplate.setHashValueSerializer(serializer);
	redisTemplate.afterPropertiesSet();
	return redisTemplate;
    }

    @Bean
    public RedisSerializer<Object> redisSerializer() {
	Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
	ObjectMapper objectMapper = new ObjectMapper();
	objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), DefaultTyping.NON_FINAL);
	serializer.setObjectMapper(objectMapper);
	return serializer;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
	RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
	RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
		.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer()))
		.entryTtl(Duration.ofHours(1)); // Redis cache expiry: 1 hour
	return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }
    
    @Bean(RedisConfig.CACHE_KEY_GENERATOR)
    public KeyGenerator cacheKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName()).append(":");
            sb.append(method.getName()).append(":");
            for (Object param : params) {
            	// 由於引數可能不同, hashCode肯定不一樣, 快取的key也需要不一樣
                sb.append(JsonUtil.toJson(param).hashCode());
            }
            return sb.toString();
        };
    }

}
