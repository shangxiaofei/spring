package com.spring.test.service.thread.queue.delayqueue;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午7:53 2018/9/2
 */
public class DelayTask implements Delayed {

    /**
     * 要执行的任务
     */
    private String doTask;

    /**
     * 任务要执行的时间
     */
    private Date date;


    /**
     * 还剩多长时间，开始执行任务
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(date.getTime()-System.currentTimeMillis(),unit) ;
    }

    /**
     * 当前对象，跟其他延时任务比，谁更先被触发
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        if(o instanceof DelayTask){
           Date otherDate= ((DelayTask) o).getDate();
           long a= date.getTime()-otherDate.getTime();
           if(a>0){
               return 1;
           }else if(a<0){
               return -1;
           }else{
               return 0;
           }
        }else{
            throw new RuntimeException("队列数据异常");
        }
    }

    public String getDoTask() {
        return doTask;
    }

    public void setDoTask(String doTask) {
        this.doTask = doTask;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
