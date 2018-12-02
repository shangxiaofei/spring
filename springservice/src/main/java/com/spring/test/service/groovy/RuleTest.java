package com.spring.test.service.groovy;

import com.dianping.zebra.shard.router.rule.engine.GroovyRuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:29 2018/10/24
 */
public class RuleTest {

    public static void main(String[] args) {
        //test01();
        test03();
    }


    public static void test01(){
        //2个库8张表
        RuleEngine dbRule = new GroovyRuleEngine("crc32(#Uid#)%2");
        RuleEngine tbRule = new GroovyRuleEngine("crc32(#Uid#).intdiv(2)%4");
        Map<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("Uid", "mtpp_123_459");
        System.out.println("dbIndex = "+dbRule.eval(valMap));
        System.out.println("tbIndex = "+tbRule.eval(valMap));
    }

    public static void test03(){
        //2个库8张表
        RuleEngine dbRule = new GroovyRuleEngine("#Uid#.substring(#Uid#.indexOf(\"_\") + 1, #Uid#.lastIndexOf(\"_\")).toLong()%4+2");
       // RuleEngine tbRule = new GroovyRuleEngine("##");
        Map<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("Uid", "mtpp_124_459");
        System.out.println("dbIndex = "+dbRule.eval(valMap));
       // System.out.println("tbIndex = "+tbRule.eval(valMap));
    }


    public static void test02(){
        Date date=new Date();
        System.out.println("时间=>"+date.getTime());
    }
}
