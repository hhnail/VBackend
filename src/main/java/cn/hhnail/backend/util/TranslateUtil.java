package cn.hhnail.backend.util;

import cn.hhnail.backend.bean.TranslateResult;
import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TranslateUtil {

    private final static String URL = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private final static String APP_ID = "20220515001216767";

    private final static String APP_KEY = "_AUEiiSMHzx8kyDDr8lf";

    private final static String SALT = "123456";

    public static void main(String[] args) {
        String result = translate("apple");
        TranslateResult translateResult = JSON.parseObject(result, TranslateResult.class);
        System.out.println(translateResult);
    }

    public static String translate(String query) {
        String result = "";

        String beforeSign = APP_ID + query + SALT + APP_KEY;

        System.out.println("beforeSign:" + beforeSign);

        String sign = DigestUtils.md5DigestAsHex(beforeSign.getBytes(StandardCharsets.UTF_8));

        System.out.println("sign:" + sign);

        String url = String.format(URL + "?" +
                        "q=%s&" +
                        "from=en&" +
                        "to=zh&" +
                        "appid=%s&" +
                        "salt=%s&" +
                        "sign=%s",
                query,
                APP_ID,
                SALT,
                sign
        );

        System.out.println("url:" + url);

        String s = HttpUtil.sendPost(url, null);


        return s;
    }
}