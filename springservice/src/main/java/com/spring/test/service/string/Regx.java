package com.spring.test.service.string;

import java.util.regex.Pattern;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午2:55 2019/1/29
 */
public class Regx {
    private static final String regx="^FT\\d{1}[a-zA-Z]\\d{4}$";

    public static void main(String[] args) {
        String mtpp="FT3S0000";
        System.out.println("匹配结果=>"+Pattern.matches(regx,mtpp));
    }

}
