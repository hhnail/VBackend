package cn.hhnail.backend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转化工具类
 */
public class MyDateUtil {

    private static SimpleDateFormat yMdFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String parseToFormatTime(Date date){
        return yMdFormat.format(date);
    }

}
