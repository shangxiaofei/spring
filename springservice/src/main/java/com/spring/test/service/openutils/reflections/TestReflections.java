package com.spring.test.service.openutils.reflections;
import org.reflections.Reflections;
import org.springframework.beans.factory.BeanFactoryAware;


import java.util.Set;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午6:39 2018/9/25
 */
public class TestReflections {
    public static void main(String[] args) {
        Reflections reflections=new Reflections("com.spring.test.service.guava");
        Set<Class<? extends BeanFactoryAware>> set=reflections.getSubTypesOf(BeanFactoryAware.class);
        for(Class cls:set){
            System.out.println("类型==>"+cls.getName());
        }

    }
}
