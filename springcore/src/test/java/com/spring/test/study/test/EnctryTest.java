package com.spring.test.study.test;

import com.spring.test.utils.CompressFileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午2:54 2019/3/22
 */
public class EnctryTest {

    private static String srcPath="/Users/shangxiaofei/sftpenc";
    private static String encPath="/Users/shangxiaofei/sftpenc/enc";
    private static String decPath="/Users/shangxiaofei/sftpenc/dec";
    private static String srcName="netsunionAcs_181_20190317.txt";
    private static String encName="GW_netsunionAcs_181_20190317.txt.zip";
    private static String password="1234567890";

    public static void main(String[] args) {
        testArray();
//        isFileD();
//            enc();
//            dec();
//            list();
    }


    public static void enc(){
        String srcFile=srcPath+ File.separatorChar+srcName;
        String dest=encPath+File.separatorChar+encName;

        try {
            CompressFileUtil.zip(srcFile,dest,password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void dec(){
        String srcFile=encPath+File.separatorChar+encName;
        String dest=decPath;
        try {
            System.out.println("srcFile:"+srcFile);
            System.out.println("detFile:"+dest);
            CompressFileUtil.unzip(srcFile,dest,password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void list(){
        File file=new File(srcPath);
       for(String a:file.list()) {
           System.out.println(a);
       }
    }

    public static void isFileD(){
        String a="/sxf/dsd/";
        System.out.println(File.separator);
        System.out.println(a.endsWith(File.separator));
    }

    public static void testArray(){
        ArrayList<String> a=new ArrayList<>();
        a.add("sxf");
        a.add("chn");
        a.add("sxq");
       String[] x= a.toArray(new String[a.size()]);
        for(String t:x){
            System.out.println(t);
        }
    }
}
