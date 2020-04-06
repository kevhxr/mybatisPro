package com.xr.config.redis;

import org.apache.ibatis.cache.Cache;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.xr.config.redis.RedisConfig.getJsonSerializer;


public class RedisCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private static RedisTemplate<String, Object> redisTemplate;

    private final String id;

    /**
     * The {@code ReadWriteLock}.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    public static void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisCache.redisTemplate = redisTemplate;
    }


    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.info("[RedisCache] MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public String getId() {

        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        try {
            redisTemplate.setValueSerializer(getJsonSerializer());
            logger.info("[RedisCache]{} putObject: key={}, value={}", id, key, value);
            if (null != value) {
                logger.info("Judge!! {}, {}", value.getClass().getName(), value);
                redisTemplate.opsForValue().set(key.toString(), value, 5, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[RedisCache] redis保存数据异常！");
        }
    }

    @Override
    public Object getObject(Object key) {
        try {
            redisTemplate.setValueSerializer(getJsonSerializer());
            logger.info("[RedisCache]{} getObject: key={}", id, key);
            if (null != key) {
                Object value = redisTemplate.opsForValue().get(key.toString());
                if (value != null)
                    logger.info("Hit!! {}, {}", value.getClass().getName(), value);
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[RedisCache] redis获取数据异常！");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        try {
            logger.info("[RedisCache]{} removeObject: key={}", id, key);
            if (null != key)
                return redisTemplate.expire(key.toString(), 1, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[RedisCache] redis remove data failure！");
        }
        return null;
    }

    @Override
    public void clear() {
        Long size = redisTemplate.execute(
                (RedisCallback<Long>) redisConnection -> {
                    Long size1 = redisConnection.dbSize();
                    //连接清除数据
                    redisConnection.flushDb();
                    redisConnection.flushAll();
                    return size1;
                });
        logger.info("[RedisCache] clear: " + size + "个对象");
    }

    @Override
    public int getSize() {
        Long size = redisTemplate.execute(
                (RedisCallback<Long>) connection -> connection.dbSize()
        );
        return size.intValue();
    }
}
