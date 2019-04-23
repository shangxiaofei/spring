package com.spring.test.service.javacore.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午5:46 2019/3/29
 */
public class FilterTest {

    public static void main(String[] args) {
        List<String> a=new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.stream().filter(t->{
            if("a".equals(t)){
                //要被过滤掉
                return false;
            }
            //留下来
            return true;
        }).forEach(t->{
            System.out.println(t);
        });
    }
}
