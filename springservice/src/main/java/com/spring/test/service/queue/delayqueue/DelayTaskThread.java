package com.spring.test.service.queue.delayqueue;



import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午7:57 2018/9/2
 */
public class DelayTaskThread extends Thread{

private DelayQueue<DelayTask> delayTasksQueue;


public DelayTaskThread(DelayQueue<DelayTask> delayTasksQueue ){
    this.delayTasksQueue=delayTasksQueue;
}

    @Override
    public void run() {
        System.out.println("===========线程开始运行=====");
       while (true){
           try {
               DelayTask delayTask=delayTasksQueue.take();
               System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")+"获取任务成功");
               Date date =delayTask.getDate();
               String task=delayTask.getDoTask();
               System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")+"=====>要执行的任务内容为【"+task+"】到期时间为【"+ DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss")+"】");
               Arrays.asList("a","b");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }catch (Exception e){
               e.printStackTrace();
           }
       }
    }
}
