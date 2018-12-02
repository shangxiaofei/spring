package com.spring.test.service.quratz;

import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午9:00 2018/7/9
 */
public class QuratzTest {

    public static void main(String[] args) throws SchedulerException, ParseException {
        test01();
    }


    public static void test01() throws ParseException {
        CronTriggerImpl cronTrigger=new CronTriggerImpl();
        TimeZone timeZone=TimeZone.getDefault();
        CronExpression cronExpression=new CronExpression("0 10 6 * * ?");
        cronExpression.setTimeZone(timeZone);
        Date date=cronExpression.getTimeAfter(new Date());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }

    public static void test02() throws SchedulerException {
        String fileName="/Users/shangxiaofei/sxfwork/sxfproject3/spring/springservice/src/main/resources/quartz.properties";

        //创建调度器Schedule
        SchedulerFactory schedulerFactory = new StdSchedulerFactory(fileName);
        Scheduler scheduler = schedulerFactory.getScheduler();
        //创建JobDetail实例，并与HelloWordlJob类绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloWorldJob.class).withIdentity("job1", "jobGroup1")
                .build();
        //创建触发器Trigger实例(立即执行，每隔1S执行一次)
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
        //开始执行
        JobKey jobKey=jobDetail.getKey();
        if(!scheduler.checkExists(jobKey)){
            scheduler.scheduleJob(jobDetail,trigger);
        }
        scheduler.start();
    }

}
