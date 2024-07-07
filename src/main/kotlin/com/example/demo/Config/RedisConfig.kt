package com.example.demo.Config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Value("\${TR_REDIS_SERVER:localhost}")
    private lateinit var redisHostname : String

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val jedisConFactory = JedisConnectionFactory()
        jedisConFactory.hostName = redisHostname
        jedisConFactory.port = 6379
        return jedisConFactory
    }


    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory, objectMapper: ObjectMapper): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.connectionFactory = connectionFactory

        // Set serializers
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)

        template.afterPropertiesSet()
        return template
    }
}
