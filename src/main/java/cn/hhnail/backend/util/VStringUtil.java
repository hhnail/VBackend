package cn.hhnail.backend.util;

import cn.hhnail.backend.enums.FormatePattern;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串工具
 */
public class VStringUtil {

    /**
     * 是否为【空】或【空字符串】
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }


    /**
     * 按照模板格式化Date为字符串
     * @param pattern
     * @param date
     * @return
     */
    public static String getFormatedDate(FormatePattern pattern, Date date){
        return new SimpleDateFormat(pattern.getPattern()).format(date);
    }
}
