package person.justin.blog.login;

import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>登录测试
 *
 * @author gym on 2023-02-20 10:57
 */
public class LoginTest {

    public static void main(String[] args) {
        getCode();
    }

    public static void getCode() {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", "blog");
        params.put("response_type", "code");
        params.put("scope", "all");
        params.put("redirect_uri", "http://localhost:8080/auth");
        params.put("username", "moming");
        params.put("password", 123);
        String result = HttpUtil.get("http://localhost:8080/oauth/authorize", params);
        System.out.println("结果：" + result);
    }
}
