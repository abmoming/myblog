package person.justin.blog.auth.provider;

import person.justin.blog.constant.TokenConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Auth提供者
 *
 * @author gym on 2023-02-27 15:03
 */
public class AuthProvider {

    public static final String AUTH_KEY = TokenConstant.TOKEN_HEADER;

    private static final List<String> DEFAULT_SKIP_URI = new ArrayList<>();

    static {
        DEFAULT_SKIP_URI.add("/oauth/captcha/**");
        DEFAULT_SKIP_URI.add("/oauth/token/**");
        DEFAULT_SKIP_URI.add("/oauth/authorize/**");
        DEFAULT_SKIP_URI.add("/oauth/check_token");
        DEFAULT_SKIP_URI.add("/login");
    }

    /**
     * 获取默认跳过的uri
     *
     * @return List<String>
     */
    public static List<String> getDefaultSkipUri() {
        return DEFAULT_SKIP_URI;
    }
}