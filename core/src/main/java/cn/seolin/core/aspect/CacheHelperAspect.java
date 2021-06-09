package cn.seolin.core.aspect;

import cn.seolin.core.annotaion.CacheHelper;
import cn.seolin.core.service.impl.RedisCacheService;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缓存帮助器切面
 *
 * @author liangzi on 2021/6/9
 */
@Aspect
public class CacheHelperAspect {
    @Autowired
    private RedisCacheService redisCacheService;

    @Around(value = "@annotation(cn.seolin.core.annotaion.CacheHelper) && @annotation(cacheHelper)")
    public Object help(ProceedingJoinPoint proceedingJoinPoint, CacheHelper cacheHelper) throws Throwable {
        String key = cacheHelper.key();
        long time = cacheHelper.time();
        //获取缓存
        Class<?> returnType = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getReturnType();
        String cache = redisCacheService.getCache(key);
        if (cache != null && cache.length() > 0) {
            //若获取到缓存，则直接返回
            return JSONObject.parseObject(cache, returnType);
        }
        //记录缓存
        Object[] args = proceedingJoinPoint.getArgs();
        Object proceed = proceedingJoinPoint.proceed(args);
        String value = JSONObject.toJSONString(proceed);
        redisCacheService.addCache(key, value, time);
        return proceed;
    }

}
