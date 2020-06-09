package com.chw.test.utils.jwt;

import com.alibaba.fastjson.JSON;
import com.chw.test.dto.MyUserDetails;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtils {

    //加密密钥
    private static final String secretKey="any word can be secret key";

    /**
     * 签发JWT
     * @param id id
     * @param subject 可以是JSON数据 尽可能少
     * @param ttlMillis 过期时间 单位ms
     * @return  String
     *
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)   // 主题
                .setIssuer("user")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }
    /**
     * 验证JWT
     * @param jwtStr jwt字符串
     * @return checkResult
     */
    public static CheckResult validateJWT(String jwtStr) {
        CheckResult checkResult =new CheckResult();
        Claims claims;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(1);
            checkResult.setErrMsg("token已过期");
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(2);
            checkResult.setErrMsg("解析token异常");
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     *
     * 解析JWT字符串
     * @param jwt jwt字符串
     * @return Claims
     * @throws Exception 异常
     */
    private static Claims parseJWT(String jwt) throws Exception {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) {
        MyUserDetails lisi = new MyUserDetails(2L, "李四", "123456");
        String wsh = JwtUtils.createJWT("wsh", JSON.toJSONString(lisi), 1800000);
        System.out.println(wsh);
    }
}