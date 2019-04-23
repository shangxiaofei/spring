package com.spring.test.service.io;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:48 2019/3/28
 */
public class FileTest {

    public static void main(String[] args) {
        deteleDec();
    }


    public static void deteleDec() {
//        File file=new File("/Users/shangxiaofei/sftpenc/tag");
        String file = "/Users/shangxiaofei/sftpenc/tag";
        deleteDirectory(file);
    }


    /**
     * 删除目录
     *
     * @param directoryPath
     * @return
     */
    public static boolean deleteDirectory(String directoryPath) {
        if (StringUtils.isBlank(directoryPath)) {
            return true;
        }
        File file = new File(directoryPath);
        return deleteDirectory(file);
    }

    /**
     * 删除目录
     *
     * @param file
     * @return
     */
    public static boolean deleteDirectory(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length <= 0) {
                return file.delete();
            }
            for (File f : files) {
                if (f.isFile() && !f.delete()) {
                    return false;
                } else if (f.isDirectory() && !deleteDirectory(f)) {
                    return false;
                }
            }
            return file.delete();
        } else {
            throw new RuntimeException("当前文件非目录");
        }
    }
}
