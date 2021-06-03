package cn.seolin.core.service.impl;

import cn.seolin.core.service.CacheService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 本地缓存
 *
 * @author liangzi on 2021/6/3
 */
public class LocalCacheService implements CacheService {
    private static Cache<String, String> localCache = null;

    @Override
    public void addCache(String key, String value, long time) {
        localCache.put(key, value);
    }

    @Override
    public String getCache(String key) {
        return localCache.getIfPresent(key);
    }

    @Override
    public void init() {
        localCache = CacheBuilder.newBuilder().build();
    }


}
