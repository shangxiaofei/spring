package com.spring.test.service.groovy;

import com.dianping.zebra.shard.router.rule.engine.GroovyRuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngine;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:29 2018/10/24
 */
public class RuleTest {

    public static void main(String[] args) {
        test03();
        System.out.println("索引如下");
        test01();
//        test08();
//        test09();
    }

    /**
     * 算索引
     */
    public static void test01(){
        RuleEngine dbRule = new GroovyRuleEngine("crc32(#Uid#).intdiv(1)%4");
        RuleEngine tbRule = new GroovyRuleEngine("crc32(#Uid#).intdiv(10)%256");
        Map<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("Uid", "21903271572200795");
        System.out.println("dbIndex = "+dbRule.eval(valMap));
        System.out.println("tbIndex = "+tbRule.eval(valMap));
    }


    /**
     * 算流水outNo-mttpp_9997771_8977743
     */
    public static void test03(){
        RuleEngine dbRule = new GroovyRuleEngine("#out_no#.substring(#out_no#.indexOf('_') + 1, #out_no#.lastIndexOf('_')).toLong()%4");
        RuleEngine tbRule = new GroovyRuleEngine("#out_no#.substring(#out_no#.indexOf('_') + 1, #out_no#.lastIndexOf('_')).toLong().intdiv(10)%256");
        Map<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("out_no", "mtpp_457624423_10913038263");
        System.out.println("dbIndex = "+dbRule.eval(valMap));
        System.out.println("tbIndex = "+tbRule.eval(valMap));
    }



    /**
     * 算索引
     */
    public static void test08(){
        RuleEngine dbRule = new GroovyRuleEngine("crc32(#Uid#).intdiv(1)%2");
        RuleEngine tbRule = new GroovyRuleEngine("crc32(#Uid#).intdiv(1)%4");
        Map<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("Uid", "outNo-mttpp_9997777_8977743");
        System.out.println("dbIndex = "+dbRule.eval(valMap));
        System.out.println("tbIndex = "+tbRule.eval(valMap));
    }


    /**
     * 算流水
     */
    public static void test09(){
        RuleEngine dbRule = new GroovyRuleEngine("#out_no#.substring(#out_no#.indexOf('_') + 1, #out_no#.lastIndexOf('_')).toLong()%2");
        RuleEngine tbRule = new GroovyRuleEngine("#out_no#.substring(#out_no#.indexOf('_') + 1, #out_no#.lastIndexOf('_')).toLong()%4");
        Map<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("out_no", "mtpp_9997673_8977743");
        System.out.println("dbIndex = "+dbRule.eval(valMap));
        System.out.println("tbIndex = "+tbRule.eval(valMap));
    }




    public static void test02(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 9 - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        System.out.println("时间=>"+ DateFormatUtils.format(calendar.getTime(),"yyyyMM"));
    }


    public static void test04(){
        RuleEngine dbRule = new GroovyRuleEngine("shardByMonth(#created_time#,'yyyyMM','201708','201812',1,1,6,0,0,true)");
        Map<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("created_time", new Date());
        System.out.println("dbIndex = "+dbRule.eval(valMap));
    }

}
