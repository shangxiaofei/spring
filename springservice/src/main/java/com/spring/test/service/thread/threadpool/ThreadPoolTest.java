package com.spring.test.service.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午2:20 2019/4/22
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        test02();


    }


    public static void test02(){
        //手动创建定时执行任务
        ScheduledExecutorService scheduledExecutorService=Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(()->{
            System.out.println("我是5秒钟执行一次任务");
        },5,5,TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(()->{
            System.out.println("我是2秒钟执行一次任务");
        },2,2,TimeUnit.SECONDS);
    }

    public static void test01(){
        // ThreadPoolExecutor threadPoolExecutor=ThreadPoolExecutor.CallerRunsPolicy

        //线程无线增长，基于SynchronousQueue，拒绝策略，丢弃并报异常
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //1个核心线程，不会增长，基于无界队列LinkedBlockingQueue，拒绝策略，丢弃并报异常
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        //固定大小的队列，无界队列LinkedBlockingQueue,拒绝策略，丢弃并报异常
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        //int corePoolSize,int maximumPoolSize,long keepAliveTime,
        //TimeUnit unit,BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
        // RejectedExecutionHandler handler

        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread=new Thread(r);
                thread.setName("sxf test thread");
                return null;
            }
        });
    }
}
