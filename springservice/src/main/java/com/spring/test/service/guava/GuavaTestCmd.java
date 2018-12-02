package com.spring.test.service.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午2:11 2018/6/5
 */
public class GuavaTestCmd {

    private static LoadingCache<String,String> loadingCache= CacheBuilder.newBuilder().initialCapacity(3).maximumSize(10).refreshAfterWrite(10, TimeUnit.SECONDS).removalListener(new RemovalListener<String, String>() {
        @Override
        public void onRemoval(RemovalNotification<String, String> notification) {
            RemovalCause cause=notification.getCause();
            String key=notification.getKey();
            String value=notification.getValue();
            System.out.println("Time=【"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())+"】key=【"+key+"】value=【"+value+"】被移除，移除原因=【"+cause+"】");
        }
    }).build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            System.out.println("需要重新加载的key为=【"+key+"】");
            return "888888";
        }
    });


//    public static void main(String[] args) throws ExecutionException {
//        loadingCache.put("key1","sxf");
//        loadingCache.put("key2","dongfang");
//
//        for(int i=0;i<20;i++){
//            int c=i+1;
//           String value= loadingCache.get("key1");
//            System.out.println("第【"+c+"】次访问key=【key1】value=【"+value+"】");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        String value2=loadingCache.get("key2");
//        System.out.println("第【1】次访问key=【key2】value=【"+value2+"】");
//    }


    public static  boolean isEmtpy(Object object){
        if(object instanceof Collection){
            Collection collection= (Collection) object;
            return collection.isEmpty();
        }
        if(object instanceof Map){
            Map map= (Map) object;
            return map.isEmpty();
        }
        return false;
    }

    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        map.put("a","sxf");
        System.out.println("map是否为空"+isEmtpy(map));
        List<String> a=new ArrayList<>();
        a.add("'");
        System.out.println("list是否为空"+isEmtpy(a));

    }
}
