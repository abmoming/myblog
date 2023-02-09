package person.justin.blog.utils;

import person.justin.blog.model.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Auth工具类
 *
 * @author gym on 2023-02-07 17:09
 */
public class AuthUtil {

    public static LoginUser getUser() {

        HttpServletRequest request = WebUtil.getRequest();
        getUser(request);

        return null;
    }

    public static LoginUser getUser(HttpServletRequest request) {

        return null;
    }
}
