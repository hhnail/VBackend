package cn.hhnail.backend.util;

import cn.hhnail.backend.enums.DateTemplate;
import cn.hhnail.backend.vo.request.Field;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Hhnail 字符串工具类
 */

@Slf4j
public class StringUtils {

    /**
     * 检查字符串是否为空
     * 空：null或空字符串""
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 检查字符串非空（null或""）
     */
    public static boolean notEmpty(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return true;
    }


    /**
     * 将 int 类型的数组转化为 string 字符串
     *
     * @param intArr    需要转化的数组
     * @param separator 分隔符
     * @return
     */
    public static String parseIntArrayToString(Integer[] intArr, String separator) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < intArr.length; i++) {
            sb.append(intArr[i]).append(separator);
        }
        sb.substring(sb.length());
        return sb.toString();
    }

    /**
     * 将String类型的date转成 java.uitl.date类型
     *
     * @param dateString 待转化的字符串
     * @param template   转化的字符串模板
     * @return
     */
    public static Date parseString2Date(String dateString, DateTemplate template) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(template.getTemplateString());
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 按照模板，将时间类型转化字符串
     *
     * @param date
     * @param template
     * @return
     */
    public static String formatDate2String(Date date, DateTemplate template) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(template.getTemplateString());
        try {
            String dateString = sdf.format(date);
            return dateString;
        } catch (Exception e) {
            e.printStackTrace();
            return sdf.format(new Date());
        }
    }

    public static String groupConcat(List<Object> list, String separator) {
        StringBuffer sb = new StringBuffer();
        list.forEach(item -> {
            sb.append(item.toString());
            sb.append(separator);
        });
        return sb.substring(0, sb.length() - separator.length());
    }
}
