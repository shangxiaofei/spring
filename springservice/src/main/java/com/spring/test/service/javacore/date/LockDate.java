package com.spring.test.service.javacore.date;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:35 2018/11/12
 */
public class LockDate {
    public static void main(String[] args) {
        test03();
    }

    public static void test01(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,2017);
        calendar.set(Calendar.MONTH,9-1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY,4);
        System.out.println(DateFormatUtils.format(calendar.getTime(),"yyyy-MM-dd HH:mm:ss"));
    }

    public static void test02(){
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("当前的小时==>"+hour);
    }

    public static void test03(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, 11 - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        System.out.println(DateFormatUtils.format(calendar.getTime(),"yyyMMdd"));
    }
}
