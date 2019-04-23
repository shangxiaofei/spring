package com.spring.test.service.javacore.string;

import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:49 2019/4/19
 */
public class StringTest {

    public static void main(String[] args) {
        //test01();
        test02();
    }

    public static void test01() {
        String json = "123,12,12.3";
        String[] str = json.split(",");
        for (String s : str) {
            System.out.println(Integer.valueOf(s));
        }
    }

    public static void test02() {
        String json = "12343 ,52343  ,89";
        String[] jsons=json.split(",");
        for(String j:jsons){
            Integer.valueOf(j);
        }
        json = StringUtils.deleteWhitespace(json);
        System.out.println(json);
    }
}
