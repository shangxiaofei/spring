package com.spring.test.service.string;

import java.util.StringTokenizer;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午7:44 2019/1/11
 */
public class StringTokenUtils {

    public static void main(String[] args) {
        String str = "100|66,55:200|567,90:102|43,54";
        StringTokenizer strToke = new StringTokenizer(str, ":,|");// 默认不打印分隔符
        // StringTokenizer strToke=new StringTokenizer(str,":,|",true);//打印分隔符
        // StringTokenizer strToke=new StringTokenizer(str,":,|",false);//不打印分隔符
        while(strToke.hasMoreTokens()){
            System.out.println(strToke.nextToken());
        }
    }
}
