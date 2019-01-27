package com.spring.test.service.javacore.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:10 2018/10/9
 */
public class ScheduleThread {

    private static ScheduledExecutorService executor= Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread=new Thread(r);
            thread.setName("ChannelGrayConfig-Heartbeat-Thread");
            return thread;
        }
    });

    public static void main(String[] args) {
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"===》运行一次");
                try {
                    throw new RuntimeException("asdf");
                }catch (Exception e){
                    System.out.println("抛出异常了");
                }

            }
        },1,5, TimeUnit.SECONDS);
    }


    public static void test01(){
        //线程池
        ThreadLocal<String> threadLocal=new ThreadLocal();
        threadLocal.set("a");
    }
}
