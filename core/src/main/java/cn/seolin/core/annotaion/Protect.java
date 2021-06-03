package cn.seolin.core.annotaion;

/**
 * 缓存是否需要保护
 * 若缓存需要保护，则在redis缓存无法使用的时候，会使用本地缓存
 *
 * @author liangzi on 2021/6/3
 */
public @interface Protect {
}
