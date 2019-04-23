package com.spring.test.service.thread.queue;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午1:27 2019/3/29
 */
public class LinkedBlockingDequeTest {

    public static void main(String[] args) {

    }

    public static void test01(){
        LinkedBlockingDeque<String> linkedBlockingDeque=new LinkedBlockingDeque();
        linkedBlockingDeque.add("peek");
        linkedBlockingDeque.addFirst("a");
    }
}
