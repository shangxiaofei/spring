package com.spring.test.service.javacore.operationsign;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午1:28 2019/4/2
 * 移位运算
 */
public class ShiftTest {
    public static void main(String[] args) {
        test01();

    }


    public static void test01() {
        int number = 10;
        //原始数二进制
        printInfo(number);
        number = number << 1;
        //左移一位
        printInfo(number);

        number = number >> 1;
        //右移一位
        printInfo(number);
    }

    /**
     * 26      * 输出一个int的二进制数
     * 27      * @param num
     * 28
     */
    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
    }
}
