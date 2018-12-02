package com.spring.test.service.guava.cache;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午11:39 2018/5/30
 */
public interface Cache<K,V> {

    public V getKey(K key);

    public boolean putKey(K key);

    public boolean detelte(K key);

}
