package com.spring.test.service.string;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午9:28 2019/1/22
 */
public class ArrayListStr {

    public static void main(String[] args) {
        test01();
    }

    public static void test01(){
        String a="mtpp_123_123,shangxiafoei, ,mtpp_456_456";
        List<String> list=Arrays.asList(a.split(","));
        System.out.println("list的长度=>"+list.size());
        list.stream().filter(s -> {
            if(StringUtils.isEmpty(s)){
                return false;
            }
            String regx="^mtpp_\\d+_\\d+$";
            return Pattern.matches(regx, s);
        }).forEach(s->{
            System.out.println("元素为=>"+s);
        });
    }


    public static void test02(){
        String s="mtpp_01099993_123";
        String regx="^mtpp_\\d+_\\d+$";
        Pattern pattern=Pattern.compile(regx);
        Matcher matcher=pattern.matcher(s);
        System.out.println("正则匹配结果=>"+matcher.matches());
        String d="mtpp_1234_54343d";
        Matcher matcher2= pattern.matcher(d);
        System.out.println("第二次匹配的结果=>"+matcher2.matches());


        boolean falg=Pattern.matches(regx,s);
        System.out.println("最终匹配规则=>"+falg);
    }
}
