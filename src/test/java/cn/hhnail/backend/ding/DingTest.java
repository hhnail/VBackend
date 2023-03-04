package cn.hhnail.backend.ding;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

/**
 * @author Hhnail
 * @version 1.0
 * @description: TODO
 * @date 2023/3/4 20:48
 */
public class DingTest {

    public static void main(String[] args) throws Exception {
        Long timestamp = System.currentTimeMillis();
        String secret = "SEC3bfefce2aab99f49f19eb5aea95e228776d2aa272dbbe5914d6bfe02c452480a";
        String stringToSign = timestamp + "\n" + secret;

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");

        JSONObject json = new JSONObject();
        json.put("msgtype", "text");
        json.put("text", new JSONObject() {{
            put("content", "钉钉官方文档写的好拉！");
        }});

        String accessToken = "dc7008ac6a594d6713ab428ae4c9dc8ca1813e5735a243f1884233c3d61e0c95";
        String url = "https://oapi.dingtalk.com/robot/send"
                + "?access_token=" + accessToken
                + "&timestamp=" + timestamp
                + "&sign=" + sign;
        String get = HttpUtil.createPost(url)
                .body(json)
                .execute().body();
        System.out.println(get);
    }
}
