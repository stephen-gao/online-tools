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

    public static boolean makeDir(String dirPath){
        File file = new File(dirPath);
        return makeDir(file);
    }

    public static boolean makeDir(File file){
        //存在
        if(file.exists()){
            return true;
        }
        //不存在
        File parentFile = file.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdir();
        }
        return file.mkdir();
    }
}
