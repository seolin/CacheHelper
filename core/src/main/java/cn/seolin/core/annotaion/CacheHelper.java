package cn.seolin.core.annotaion;

import java.lang.annotation.*;

/**
 * 缓存帮助器，帮助开发快速使用缓存
 *
 * @author liangzi on 2021/6/3
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheHelper {

    /**
     * 需要全局唯一
     *
     * @return 缓存中的key
     */
    String key();

    /**
     * 单位毫秒
     *
     * @return 过期时间
     */
    long time();

    /**
     * 默认否
     *
     * @return 是否为本地缓存
     */
    boolean local() default false;
}
