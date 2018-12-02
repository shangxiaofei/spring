package com.spring.test.service.string;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:39 2018/9/13
 */
public class StringFormatTest {

    public static void main(String[] args) {
        test05();
    }



    public static void test03(){
        List<String> strs=new ArrayList<>();
        strs.add(null);
        for(String d:strs){
            System.out.println("====>"+d.trim());
        }
    }
    public static void test01() {
        String template = "当前时间为：%tF %tT";
        Date date =new Date();
        String result = String.format(template,date,date);
        System.out.println(result);
    }

    public static void test02(){
        String name="";
        try {
            name="为抛出异常前";
            int a=1/0;
        }catch (Exception e){
            name="抛出异常后";
            e.printStackTrace();
        }finally {
            System.out.println(name);
        }
    }

    public static void test04(){
        Map<String,Integer> integerMap=new HashMap<>();
        Integer i =integerMap.get("a");
    }


    public static void test05(){
         String CHANNEL_TIMER_TASK_LOCK_KEY_OPERATION = "channelTimerTask_%d_%d_%s_operation";
         String key=String.format(CHANNEL_TIMER_TASK_LOCK_KEY_OPERATION, 171, 1, "safdsa");
        System.out.println("key==>"+key);
    }
}
