package com.xr.config.redis;

import com.xr.config.annotation.ConditionalOnSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
@Configuration
@ConditionalOnSystemProperty(name = "mode", value = "redis")
public class RedisCacheTransfer {

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {

        RedisCache.setRedisTemplate(redisTemplate);
    }
}
