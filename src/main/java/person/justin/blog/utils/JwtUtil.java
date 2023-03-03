package person.justin.blog.utils;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import person.justin.blog.constant.JwtConstant;
import person.justin.blog.jwt.prop.JwtProperties;
import person.justin.blog.redis.BlogRedis;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <p>Jwt工具类
 *
 * @author gym on 2023-02-28 15:31
 */
@Slf4j
public class JwtUtil {

    private static JwtProperties jwtProperties;

    private static BlogRedis blogRedis;

    public static final String BEARER = "bearer";

    /**
     * token前缀标识长度：bearer + 空格
     */
    public static final int AUTH_PRE_LENGTH = 7;

    public static void setJwtProperties(JwtProperties jwtProperties) {
        JwtUtil.jwtProperties = jwtProperties;
    }

    public static void setBlogRedis(BlogRedis blogRedis) {
        JwtUtil.blogRedis = blogRedis;
    }

    /**
     * 获取token
     *
     * @param auth token内容
     * @return String token
     */
    public static String getToken(String auth) {

        if (StrUtil.isBlank(auth) || (auth.length() <= AUTH_PRE_LENGTH)) {
            return null;
        }

        String bearer = auth.substring(0, AUTH_PRE_LENGTH - 1).toLowerCase();
        if (BEARER.equals(bearer)) {
            auth = auth.substring(AUTH_PRE_LENGTH);
        }
        return auth;
    }

    /**
     * 解析token
     *
     * @param token token内容串
     * @return Claims
     */
    public static Claims parseJwt(String token) {

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getSignKey().getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("token:{}，解析异常", token, e);
            return null;
        }
    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2Nzc2ODQ5ODUsInVzZXJfbmFtZSI6Im1vbWluZyIsImF1dGhvcml0aWVzIjpbIlJPTEVfYWRtaW4iLCJST0xFX2Fub255bWl0eSIsIlJPTEVfdXNlciJdLCJqdGkiOiJrSFVsX1ZneW90UFRTRC1qUnFudTlvd21wYUEiLCJjbGllbnRfaWQiOiJibG9nIiwic2NvcGUiOlsiYWxsIl19.EgWmiHf-zbzUMe8aBOk9EtVUkr2ZE6X1Xmrs8aiKy1Y";
        /*String str = "bladexisapowerfulmicroservicearchitectureupgradedandoptimizedfromacommercialproject";
        System.out.println("bladexisapowerfulmicroservicearchitectureupgradedandoptimizedfromacommercialproject".length());
        String encode = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        byte[] decode = Base64.getDecoder().decode(encode);*/
        Claims claims = parseJwt(token);
        System.out.println(JsonUtil.toJsonStr(claims));
    }
}