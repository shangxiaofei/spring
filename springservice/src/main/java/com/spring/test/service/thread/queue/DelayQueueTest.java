package com.spring.test.service.thread.queue;

import com.spring.test.service.thread.queue.delayqueue.DelayTask;

import java.util.concurrent.DelayQueue;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午3:11 2019/4/3
 */
public class DelayQueueTest {

    public static void main(String[] args) {

    }


    public static  void test01() throws InterruptedException {
        DelayQueue<DelayTask> delayQueue=new DelayQueue<DelayTask>();
        //添加一个延迟任务
        delayQueue.add(new DelayTask());
        //从延迟队列里获取一个任务
        delayQueue.take();
    }
}
