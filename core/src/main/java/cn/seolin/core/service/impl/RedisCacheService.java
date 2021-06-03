package cn.seolin.core.service.impl;

import cn.seolin.core.service.CacheService;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * redis缓存服务
 *
 * @author liangzi on 2021/6/3
 */
public class RedisCacheService implements CacheService {

    private RedissonClient redissonClient;

    @Override
    public void addCache(String key, String value, long time) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value, time, TimeUnit.MILLISECONDS);
    }

    @Override
    public String getCache(String key) {
        return redissonClient.getBucket(key).get().toString();
    }

    @Override
    public void init() {
        //TODO:从配置文件中取创建方法
        Config config = new Config();
        redissonClient = Redisson.create(config);
    }
}
