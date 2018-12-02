package com.spring.test.service.queue;

import com.spring.test.service.queue.delayqueue.DelayTask;
import com.spring.test.service.queue.delayqueue.DelayTaskThread;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午7:53 2018/9/2
 */
public class TestClass {

    public static void main(String[] args) {
        testDelayQueue();
    }

    public static void testDelayQueue(){
        DelayQueue<DelayTask> delayTasks=new DelayQueue<>();
        DelayTaskThread delayTaskThread=new DelayTaskThread(delayTasks);
        delayTaskThread.start();


        DelayTask delayTask1=new DelayTask();
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,50);
        Date date=calendar.getTime();
        delayTask1.setDoTask("delayTask1");
        delayTask1.setDate(date);
        System.out.println("delayTask1 执行时间为==>"+ DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss"));

        DelayTask delayTask2=new DelayTask();
        Calendar calendar2=Calendar.getInstance();
        calendar2.add(Calendar.SECOND,30);
        Date date2=calendar2.getTime();
        delayTask2.setDoTask("delayTask2");
        delayTask2.setDate(date2);
        System.out.println("delayTask2 执行时间为==>"+ DateFormatUtils.format(date2,"yyyy-MM-dd HH:mm:ss"));

        delayTasks.add(delayTask1);
        delayTasks.add(delayTask2);
    }

}
