package com.spring.test.utils;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.print.attribute.HashPrintJobAttributeSet;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午3:17 2018/6/11
 */
public class HashMapUtils {
    //最大的个数
    static final int MAXIMUM_CAPACITY = 1 << 30;


    /**
     * 计算一个hashMap的初始化大小
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {

        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * 计算他的扩容大小
     * @param newCap
     * @param loadFactor
     * @return
     */
    static final int initThreald(int newCap,float loadFactor){
        float ft = (float)newCap * loadFactor;
        int newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                (int)ft : Integer.MAX_VALUE);
        return newThr;
    }


    public int compu(){
        int mapLenthg=101;
        int a=tableSizeFor(mapLenthg);
        System.out.println("hashMap的初始化大小=>"+a);
        int b=initThreald(a,0.75f);
        System.out.println("最大临界值=>"+b);
        int x=mapLenthg-b;
        System.out.println("相差多少个=>"+x);
        int newLength=mapLenthg+x;
        int newSize=tableSizeFor(newLength);
        System.out.println("新HashMap的个数=》"+newSize);
        int newThread=initThreald(newSize,0.75f);
        System.out.println("新的临界值"+newThread);
        return 0;
    }
    public static void main(String[] args) {
        Map<String,String> hasMap=new HashMap<String, String>(10,0.75f);
        hasMap.put("a","b");
        computeInit();
    }

    public static void computeInit(){
        int mapLenthg=2;
        int he= (int) (mapLenthg/0.75);
        System.out.println("应该初始化的大小为=>"+he);
        int a=tableSizeFor(he);
        System.out.println("hashMap的初始化大小=>"+a);
        int newthread=initThreald(a,0.75f);
        System.out.println("初始化的临界值"+newthread);
    }
}
