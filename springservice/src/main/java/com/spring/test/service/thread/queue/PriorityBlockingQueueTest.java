package com.spring.test.service.thread.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午1:08 2019/4/2
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) {

    }

    public static void test01() throws InterruptedException {
        PriorityBlockingQueue<String> strings=new PriorityBlockingQueue<>();
        strings.add("a");
        String a=strings.take();
        "a".compareTo("b");

    }
}
