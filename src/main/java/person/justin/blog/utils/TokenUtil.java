package person.justin.blog.utils;

import person.justin.blog.constant.TokenConstant;

/**
 * <p>token工具类
 *
 * @author gym on 2023-02-22 18:57
 */
public class TokenUtil {

    /**
     * 请求头验证码key标识
     */
    public static final String CAPTCHA_KEY = "Captcha-Key";
    /**
     * 请求头验证码code标识
     */
    public static final String CAPTCHA_CODE = "Captcha-Code";
    /**
     * 用户名
     */
    public static final String USERNAME = TokenConstant.USERNAME;
    /**
     * 用户密码
     */
    public static final String PASSWORD = TokenConstant.PASSWORD;
    /**
     * 验证码不正确
     */
    public static final String CAPTCHA_NOT_CORRECT = "验证码不正确";
}
