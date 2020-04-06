package com.xr.service.modules;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Service
public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;


    public void getTest(){
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set("start","yes");
        Object start = redisTemplate.opsForValue().get("start");
        System.out.println(start.toString());
    }
}
