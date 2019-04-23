package com.spring.test.service.thread.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午9:42 2019/3/25
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger();
        System.out.println(count.get());
        System.out.println(count.getAndIncrement());
        System.out.println("当前值=>"+count.get());
        System.out.println(count.getAndIncrement());
        System.out.println("当前值=>"+count.get());
    }

    public static void test01() throws InterruptedException {
        LinkedBlockingQueue<String> linkedBlockingQueue=new LinkedBlockingQueue<>();
        linkedBlockingQueue.put("a");
        linkedBlockingQueue.add("a");
        linkedBlockingQueue.take();
    }
}
