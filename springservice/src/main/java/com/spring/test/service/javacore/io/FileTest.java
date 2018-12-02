package com.spring.test.service.javacore.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:42 2018/9/19
 */
public class FileTest {
    public static void main(String[] args) throws IOException {

        String path="/Users/shangxiaofei/sxf";
        File file=new File(path);
        if(file.exists()){
            System.out.println("==========文件存在=======");
            FileInputStream fileInputStream=new FileInputStream(file);
            System.out.println("内容为==>"+fileInputStream.read());

        }else {
            System.out.println("==========文件不存在=======");
        }
    }
}
