package com.gs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/07/08 17:16
 **/
public class DateUtils {

    public static final String defaultSimpleFormater = "yyyy-MM-dd HH:mm:ss";
    public static final String format1 = "yyyy-MM-dd";
    public static final String format2 = "yyyyMMddHHmmss";

    /**
     * 格式化日期
     *
     * @param date
     * @param formatString
     * @return
     */
    public static String format(Date date, String formatString) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(formatString);
            return df.format(date);
        }
    }

    public static String defaltFormat(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(defaultSimpleFormater);
            return df.format(date);
        }
    }


    public static void main(String[] args) {
        Date date = new Date();
        String format = format(date, format1);
        System.out.println(format);
    }
}
