package com.spring.test.service.thread.threadlocal;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午11:18 2018/8/22
 */
public class ThreadLocal {
    private static java.lang.ThreadLocal<String> threadLocal=new java.lang.ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.get();
        threadLocal.set("a");
        threadLocal.remove();
    }
}
