package com.spring.test.service.javacore.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午7:01 2018/9/29
 */
public class TestReadAndWriteLock {
    public static void main(String[] args) {


    }




    public static void testLock() throws InterruptedException {
        ReentrantLock reentrantLock=new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
        Condition condition=reentrantLock.newCondition();
        condition.await();

    }


    public static void testReadWriteLock(){

    }


}
