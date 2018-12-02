package com.spring.sxf.test;

import com.spring.test.entry.User;
import com.spring.test.entry.generator.SUser;
import com.spring.test.service.UserService;
import com.spring.test.service.impl.UserListen;
import com.spring.test.service.impl.UserManage;
import com.spring.test.service.quratz.HelloWorldJob;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午5:11 2018/5/13t
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-spring-service.xml","classpath:applicationContext-spring-core.xml"})
public class TestTranscation {
    @Autowired
    private UserService userService;

    @Autowired
    private UserManage userManage;

    @Autowired
    private UserListen userListen;

    @Test
    public  void  test(){
        User user=new User();
        user.setName("shnd尚晓飞");
        user.setAge(10);
        user.setAddress("二级缓存");
        userService.addUser(user);
    }

    @Test
    public void testCache(){
        String result=userService.getUserById("123",888);
        System.out.println("执行结果==>"+result);
    }

    @Test
    public void testCglibCahe(){
        User user=new User();
        user.setName("测试缓存");
        user.setAge(10);
        user.setAddress("二级缓存");
        String a=userManage.addUser(user);
        System.out.println(a);
    }

    @Test
    public void testSuserMapper(){
        SUser sUser=userManage.getUser();
        System.out.println("得到的名字为"+sUser.getName());
    }

    @Test
    public void testAspect(){
        userManage.doSpringAopTest("throw");
    }

    @Test
    public void testAspectRound(){
        int a=userManage.doAdd("尚晓飞",108);
        System.out.println("返回结果为==>"+a);
    }

    @Test
    public void testMoreAspectRound(){

        //userListen.save(null,null,null);
        userListen.saveNoPara();
    }

    public static void main(String[] args) throws IOException {
        Map<String,String> map=new HashMap<>();
        List<String> list=new ArrayList<>();
        Set<String> set=new HashSet<>();
        Object map1=map;
        Object list1=list;
        Object set1=set;

        if(map1 instanceof Collection){
            System.out.println("Map 是空的结果判断=>"+((Collection) map1).isEmpty());
        }
        if(list1 instanceof Collection){
            System.out.println("list1 是空的结果判断=>"+((Collection) list1).isEmpty());
        }
        if(set1 instanceof Collection){
            System.out.println("set1 是空的结果判断=>"+((Collection) set1).isEmpty());
        }
    }


    public static String zip(byte[] b) throws IOException {
        if (b == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(b);
        gzip.finish();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(out.toByteArray());
    }
    public static String zip1(byte[] b) throws IOException {
        if (b == null) {
            return null;
        }
        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);){
            gzip.write(b);
            gzip.close();
            gzip.finish();
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(out.toByteArray());
        }

    }

    public static String zip3(byte[] b) throws IOException {
        if (b == null) {
            return null;
        }
        ByteArrayOutputStream out=null;
        GZIPOutputStream gzip= null;

        try {
             out = new ByteArrayOutputStream();
             gzip = new GZIPOutputStream(out);
            gzip.write(b);
            BASE64Encoder encoder = new BASE64Encoder();

            return encoder.encode(out.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                out.close();
            }
            if(gzip!=null){
                gzip.close();
            }
        }

        return null;
    }

    @Test
    public void testQuartz() throws SchedulerException {
        Properties properties=new Properties();
        //创建调度器Schedule
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
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
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
