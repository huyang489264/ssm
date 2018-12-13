package com.itheima.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    //日期转字符串
    public static String dateToString(Date date,String patten){
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(date);
    }


    //字符串转日期
    public static Date stringToDate(String str,String patten) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.parse(str);
    }
}
