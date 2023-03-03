package person.justin.blog.login;

import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>登录测试
 *
 * @author gym on 2023-02-20 10:57
 */
public class LoginTest {

    public static void main(String[] args) {
        LoginTest test = new LoginTest();
        // getCode();
        test.testAuthFilter();




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

    public void testAuthFilter() {
        // Thread consumer = new Thread(this::post);
        // consumer.start();
        // Thread.sleep(60000);
        // consumer.interrupt();
        // while (!Thread.interrupted()) {
            String url = "http://localhost:8080/oauth/token";
            Map<String, Object> body = new HashMap<>();
            body.put("client_id","blog");
            body.put("client_secret",123);
            body.put("grant_type","captcha");
            body.put("redirect_uri","http://localhost:8080/auth");
            body.put("username","moming");
            body.put("password",123);
            String post = HttpUtil.post(url, body);
            System.out.println("输出内容："+post);
        // }
    }
}
