package cn.hhnail.backend.util;

import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.UUID;

/**
 * 加密工具类
 * <p>
 * 单例模式
 */
public class EncryptUtil {

    // 超时时间
    private static long expiration = 1000 * 60 * 60 // 一小时
            // * 24; // 1天
            * 24 * 3; // 3天
    // 密钥
    private final static String signature = "hhnail";

    private final static EncryptUtil INSTANCE = new EncryptUtil();

    public EncryptUtil() {

    }

    public EncryptUtil getInstance() {
        return INSTANCE;
    }

    public static String getJwtToken() {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // payload
                .claim("userName", "张三")
                .claim("role", "admin")
                .setSubject("admin-test")
                // .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setId(UUID.randomUUID().toString())
                // signature
                .signWith(SignatureAlgorithm.HS256, signature)
                // 用.拼接起来
                .compact();
        return jwtToken;
    }

    /**
     * 解析JwtToken
     *
     * @param jwtToken
     * @return
     */
    public static Boolean checkJwtToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(getJwtToken());
    }
}
