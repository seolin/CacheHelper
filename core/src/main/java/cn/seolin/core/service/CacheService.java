package cn.seolin.core.service;

/**
 * 缓存服务
 *
 * @author liangzi on 2021/6/3
 */
public interface CacheService {

    /**
     * 添加缓存
     *
     * @param key   缓存的key
     * @param value 对应的值
     * @param time  超时时间
     */
    void addCache(String key, String value, long time);

    /**
     * 获取缓存
     *
     * @param key 缓存的key
     * @return 缓存内容
     */
    String getCache(String key);
}
