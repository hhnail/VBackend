package cn.hhnail.backend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志工具类
 */
public class VLogUtil {

    /**
     * 获取日志前缀
     * @return
     */
    public static String getLogTemplatePefix(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateStr = sdf.format(new Date());
        return dateStr;
    }
}
