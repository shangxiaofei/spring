package com.spring.test.service.forkjoin;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Java8Test {


    public static void main(String[] args) {
        testList();
    }

    public static void testList(){
        //源数据
        List<Integer> integerList=new ArrayList<>();
        integerList.add(0);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        integerList.add(6);
        integerList.add(7);
        integerList.add(8);
        integerList.add(9);

        //java的顺序操作
        for(Integer a:integerList){
            System.out.println(Thread.currentThread().getName()+"==>"+a);
        }


        //java的并行操作
        integerList.parallelStream().forEach(integer -> {
            System.out.println(Thread.currentThread().getName()+"==>"+integer);
        });


    }
}
