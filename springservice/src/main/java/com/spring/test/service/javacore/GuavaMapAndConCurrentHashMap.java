package com.spring.test.service.javacore;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午6:13 2018/9/14
 */
public class GuavaMapAndConCurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap<String,Object>  map=new ConcurrentHashMap<>();
        map.put("1","123");

        LoadingCache<String,Object> loadingCache=CacheBuilder.newBuilder().initialCapacity(10).refreshAfterWrite(30, TimeUnit.SECONDS).maximumSize(100).removalListener(new RemovalListener<String, Object>() {
            @Override
            public void onRemoval(RemovalNotification<String, Object> notification) {

            }
        }).build(new CacheLoader<String, Object>() {
            @Override
            public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
                return super.reload(key, oldValue);
            }

            @Override
            public Map<String, Object> loadAll(Iterable<? extends String> keys) throws Exception {
                return super.loadAll(keys);
            }

            @Override
            public Object load(String key) throws Exception {
                return null;
            }
        });

        loadingCache.put("a","b");
    }
}
