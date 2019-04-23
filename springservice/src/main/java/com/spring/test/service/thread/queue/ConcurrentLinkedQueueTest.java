package com.spring.test.service.thread.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:43 2019/4/3
 */
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {

    }

    public static void test01(){
        ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue();
        queue.add("A");
    }
}
