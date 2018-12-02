package com.spring.test.service.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import com.spring.test.service.commons.BeanCopier.CacheValue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午10:18 2018/7/5
 */
public class GuavaCacheTest {



    private  static  ExecutorService service = Executors.newFixedThreadPool(10, new ThreadFactory() {

        private AtomicInteger atomicInteger = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("GuavaCache-asyncLoadCacheValue-Thread" + atomicInteger.getAndIncrement());
            return thread;
        }
    });

    private static ListeningExecutorService backgroundRefreshPools= MoreExecutors.listeningDecorator(MoreExecutors.listeningDecorator(service));;

    public static LoadingCache<String,String> loadingCache= CacheBuilder.newBuilder().initialCapacity(10).maximumSize(100).removalListener(new RemovalListener<String, String>() {
        @Override
        public void onRemoval(RemovalNotification<String, String> notification) {
            System.out.println("有一个缓存元素被移除：key=["+notification.getKey()+"],value=["+notification.getValue()+"]移除的原因=【"+notification.getCause()+"】");
        }
    }).refreshAfterWrite(10, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            return  "数据库加载";
        }

        @Override
        public ListenableFuture<String> reload(String key, String oldValue) throws Exception {

            ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    Thread.sleep(60000);
                    return "异步去数据库加载成功";
                }
            });

            backgroundRefreshPools.submit(task);
            return task;
        }
    });


    public static void TestCacheException() throws ExecutionException {
        loadingCache.get("123");
        try {
            Thread.sleep(12000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            String a=loadingCache.get("123");
            System.out.println("获取的结果为："+a);
        } catch (ExecutionException e) {
            System.out.println("加载有异常");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("记载异常");
            e.printStackTrace();
        }


        try {
            String a1=loadingCache.get("123");
            System.out.println("获取的结果为："+a1);
        } catch (ExecutionException e) {
            System.out.println("加载有异常");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("记载异常");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException {
        TestCacheException();
    }
}
