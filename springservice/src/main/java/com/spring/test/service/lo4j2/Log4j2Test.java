package com.spring.test.service.lo4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午5:07 2018/5/25
 */
public class Log4j2Test {

    private static Logger logger = LogManager.getLogger("AsyncFileLogger");

    public static void main(String[] args) {

        logger.info("天天向上");
        logger.info("好好学习");
        System.out.println("测试完毕");
        test01();
    }

    public static void test01(){
        File file=new File("/Users/shangxiaofei/sxftestLog4j2.log");
        try (FileInputStream inputStream = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader);) {
            String line = null;
            while (null != (line = bufferedReader.readLine())) {
                System.out.println(line);
            }
        } catch (Exception e) {

        }
    }

}
