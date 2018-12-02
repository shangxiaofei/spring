package com.spring.test.service.javacore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午2:18 2018/9/17
 */
public class Java8 {

    public Java8() {
    }

    public static void main(String[] args) {
        List<String> list=null;//new ArrayList<>();
        //list.add("abc");
        list.stream().forEach(a->{
            System.out.println(a);
        });
    }
}
