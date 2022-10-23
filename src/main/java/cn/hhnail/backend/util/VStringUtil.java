package cn.hhnail.backend.util;

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
}
