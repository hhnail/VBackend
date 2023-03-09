package cn.hhnail.backend;

import cn.hhnail.backend.enums.FormatePattern;
import cn.hhnail.backend.util.VStringUtil;
import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class JwtTest {

    // 超时时间
    private long expiration = 1000 * 60 * 60 * 24;
    // 密钥
    private String signature = "hhnail";


    /**
     * 加密
     * 获取JwtToken
     */
    @Test
    public void testJwtEncrypt() {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // payload
                .claim("userName", "张三")
                .claim("role", "admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setId(UUID.randomUUID().toString())
                // signature
                .signWith(SignatureAlgorithm.HS256, signature)
                // 用.拼接起来
                .compact();
        System.out.println(jwtToken);
    }


    /**
     * 解密
     */
    @Test
    public void testJwtDecrypt() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6IuW8oOS4iSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2NjcxMTcyNDAsImp0aSI6IjcwY2JmMGIwLTRiNDMtNDQzYS1iMmJhLTBmZGY1MTQzNGFhMSJ9.bXwWtm0Cxt-xnQRJqZAmI1BfkN5XsoLG76MdDDzTAC8";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        System.out.println(claims.get("userName"));
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(VStringUtil.getFormatedDate(FormatePattern.yyyyMMdd_HHmmss,claims.getExpiration()));

    }


}
