package com.spring.test.service.javacore.collections;

import java.util.Arrays;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午11:07 2018/11/6
 */
public class ArrayListTest {
    public static void main(String[] args) {
        test01();
    }

    public static void test01(){
        String a="999349,9,11,3,243,423123,3545,123124523,999349";

        System.out.println("包含的结果=>"+Arrays.asList(a.split(",")).contains(String.valueOf(9)));
    }
}
