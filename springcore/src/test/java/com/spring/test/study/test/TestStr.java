package com.spring.test.study.test;

import com.alibaba.druid.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:02 2018/5/15
 */
public class TestStr {
    public static void main(String[] args) {
        test02();
    }


    public static void test01() {
        String regex = "<|>|</|/>|\"|'|&|/";
        Pattern pattern = Pattern.compile(regex);
        String[] a = {"sd<dad>asdf|sadf\"dasfdsad", "shangxi\"</>ao/>fei程海娜"};
        for (int i = 0; i < a.length; i++) {
            String b = a[i];
            Matcher matcher = pattern.matcher(b);
            boolean flag = matcher.find();
            if (flag) {
                System.out.println("是否匹配到==>" + flag);
                System.out.println("匹配之前==>" + b);
                String d = matcher.replaceAll("");
                System.out.println("匹配之后==>" + d);
            }

        }
    }

    public static void test02(){
        String[] text={"abcd","efg","hijk","lmnopq"};
        StringBuilder stringBuilder=new StringBuilder("rst");
        int offset=0;
        for(int i=0;i<text.length;i++){
            String d=text[i];
            stringBuilder.insert(offset,d);
            offset += d.length();
        }
        stringBuilder.append("uvwxyz");
        System.out.println(stringBuilder.toString());

    }
}
