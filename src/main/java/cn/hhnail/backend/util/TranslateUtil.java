package cn.hhnail.backend.util;

import cn.hhnail.backend.bean.TranslateResult;
import cn.hhnail.backend.config.PatternProperties;
import cn.hhnail.backend.config.ThirdApiProperties;
import cn.hhnail.backend.enums.Languages;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 翻译工具类
 */
@Component
@Data
// @ConfigurationProperties(prefix = "third-api")
// @PropertySource(value="classpath:third-api.properties")
// @RefreshScope // nacos自动更新配置
public class TranslateUtil {

    // TODO yml注入配置
    // @Value(value = "${third-api.baidu-translate.url}")
    // @Value(value = "${url}")
    // private static String URL;

    // @Value(value = "${third-api.baidu-translate.url}")
    // private String URL;
    private final static String URL = "http://api.fanyi.baidu.com/api/trans/vip/translate";


    private final static String PARAMS = "?q=%s&from=%s&to=%s&appid=%s&salt=%s&sign=%s";

    private final static String APP_ID = "20220515001216767";

    private final static String APP_KEY = "_AUEiiSMHzx8kyDDr8lf";

    private final static String SALT = "123456";

    // public static void main(String[] args) {
    //     String result = translate("apple");
    //     System.out.println(result);
    // }

    @Autowired
    private PatternProperties patternProperties;
    @Autowired
    private ThirdApiProperties thirdApiProperties;

    /**
     * 最简单的API
     * 将英语转化为中文
     */
    public static String translate(String query) {
        // 默认从英语转化为中文
        return translate(query,Languages.ENGLISH,Languages.CHINESE);
    }

    /**
     * 翻译
     * @param query 需要翻译的词
     * @param from 从xx语言翻译
     * @param to 翻译成xx语言
     * @return 翻译的结果
     */
    public static String translate(String query,Languages from,Languages to) {
        // TranslateUtil translateUtil = new TranslateUtil();
        // 加密前的签名

        // thirdApiProperties.getBaiduTranslate()

        String beforeSign = APP_ID + query + SALT + APP_KEY;
        // 使用MD5算法，加密后的签名
        String sign = DigestUtils.md5DigestAsHex(beforeSign.getBytes(StandardCharsets.UTF_8));
        String url = String.format(URL + PARAMS,
                // 需要翻译的文字
                query,
                // 从语种from转化为语种to
                from.getCode(),
                to.getCode(),
                // 应用ID、盐、加密签名
                APP_ID, SALT, sign);
        String s = HttpUtil.sendPost(url, null);
        TranslateResult translateResult = JSON.parseObject(s, TranslateResult.class);
        return translateResult.getTrans_result().get(0).getDst();
    }



}