package com.spring.test.service.guava.ListenFuture;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午9:19 2018/7/5
 */
public class ListenFuture {
    public static void main(String[] args) {
        testListenFuture();
    }

    public static void testListenFuture() {
        System.out.println("主任务执行完，开始异步执行副任务1.....");
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> future = pool.submit(new Task());
        System.out.println("副本任务启动,回归主任务线，主业务正常返回2.....");

       // Futures.transform();
    }
}

class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        System.out.println("执行任务的线程名字==>"+Thread.currentThread().getName());
        // int a =1/0;
        return "task done";
    }
}
