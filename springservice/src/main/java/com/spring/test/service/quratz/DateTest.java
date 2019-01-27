package com.spring.test.service.quratz;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午12:03 2019/1/2
 */
public class DateTest {

    private static final int ZERO = 0;
    private static final int NEGATIVE_ONE = -1;
    private static final int POSITIVE_ONE = 1;

    public static void main(String[] args) throws ParseException {


        test02();
    }


    private static void test02() throws ParseException {
        String startDate = "2018-12-30 23:40:59 999";
        String endDate = "2019-01-15 00:40:59 998";
        Date start = DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss SSS");
        Date end = DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss SSS");
        int a = compareForHHmmssSSS(start, end);
        System.out.println("比较结果：" + a);


    }

    private static void test01() throws ParseException {
        long oneDay = 1000 * 60 * 60 * 24;
        String startDate = "2018-12-30 14:00:00";
        String endDate = "2019-01-15 09:00:00";
        Date start = DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startD = calendar.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(end);
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);
        Date endD = calendar2.getTime();
        System.out.println("开始日期=>" + DateFormatUtils.format(startD, "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println("结束日期=>" + DateFormatUtils.format(endD, "yyyy-MM-dd HH:mm:ss SSS"));
        for (long i = startD.getTime(); i <= endD.getTime(); i += oneDay) {
            System.out.println("日期列表=>" + DateFormatUtils.format(new Date(i), "yyyy-MM-dd HH:mm:ss SSS"));
        }
    }


    public static int compareForHHmmssSSS(Date date1, Date date2) {
        Calendar date1Calendar = Calendar.getInstance();
        date1Calendar.setTime(date1);
        List<Integer> date1List = new ArrayList<>();
        date1List.add(date1Calendar.get(Calendar.HOUR_OF_DAY));
        date1List.add(date1Calendar.get(Calendar.MINUTE));
        date1List.add(date1Calendar.get(Calendar.SECOND));
        date1List.add(date1Calendar.get(Calendar.MILLISECOND));
        Calendar date2Calendar = Calendar.getInstance();
        date2Calendar.setTime(date2);
        List<Integer> date2List = new ArrayList<>();
        date2List.add(date2Calendar.get(Calendar.HOUR_OF_DAY));
        date2List.add(date2Calendar.get(Calendar.MINUTE));
        date2List.add(date2Calendar.get(Calendar.SECOND));
        date2List.add(date2Calendar.get(Calendar.MILLISECOND));
        for (int i = 0; i < date1List.size(); i++) {
            int date1Point = date1List.get(i);
            int date2Point = date2List.get(i);
            if (date1Point > date2Point) {
                return POSITIVE_ONE;
            } else if (date1Point < date2Point) {
                return NEGATIVE_ONE;
            }
        }
        return ZERO;
    }
}
