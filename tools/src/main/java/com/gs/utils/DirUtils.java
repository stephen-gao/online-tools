package com.gs.utils;

import java.io.File;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/07/08 18:18
 **/
public class DirUtils {

    public static boolean makeDir(String dirPath) {
        File file = new File(dirPath);
        return makeDir(file);
    }

    public static boolean makeDir(File file) {
        //存在
        if (file.exists()) {
            return true;
        }
        //不存在
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdir();
        }
        return file.mkdir();
    }

    public static boolean delDir(String path) {
        return delDir(new File(path));
    }

    public static boolean delDir(File file) {
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    delDir(files[i]);
                }
                file.delete();
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String file = "D:\\test\\pdf\\out\\2020-07-09";
        boolean b = delDir(file);
        System.out.println(b);
    }
}
