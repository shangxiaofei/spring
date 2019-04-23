package com.spring.test.service.javacore.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午5:19 2018/11/5
 */
public class HashMapTest {


    public static void main(String[] args) {
        test04();
    }

    public static void test01(){
        HashMap<String, Integer> myHashMap = new HashMap<>();
        myHashMap.put("1", 1);
        myHashMap.put("2", 2);
        for (Map.Entry<String, Integer> item : myHashMap.entrySet()){
            myHashMap.remove(item.getKey());
        }
        for (Map.Entry<String, Integer> item : myHashMap.entrySet()){
            System.out.println(item.getKey());
        }
    }

    public static void test02(){
        HashMap<String, Integer> myHashMap = new HashMap<>();
        myHashMap.put("1", 1);
        myHashMap.put("2", 2);
        myHashMap.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                myHashMap.remove(s);
            }
        });
    }


    public static void test03(){
        HashMap<String, Integer> myHashMap = new HashMap<>();
        myHashMap.put("1", 1);
        myHashMap.put("2", 2);
        Iterator iterator=myHashMap.entrySet().iterator();
        while(iterator.hasNext()){
           Map.Entry<String,Integer> a= (Map.Entry<String, Integer>) iterator.next();
          String key= a.getKey();
          myHashMap.remove(key);
        }
    }


    public static void test04(){
        HashMap<String, Integer> myHashMap = new HashMap<>();
        myHashMap.put("1", 1);
        myHashMap.put("2", 2);
        myHashMap.entrySet().stream().filter(entry->{
            return  (1==entry.getValue());
        }).forEach(entry -> {
            System.out.println("结果=>"+entry.getValue());
        });
    }
}
