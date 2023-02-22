package person.justin.blog.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import person.justin.blog.model.RespBean;
import person.justin.blog.utils.WebUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>登录成功处理
 *
 * @author gym on 2023-02-17 15:39
 */
@Component
public class BlogAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        WebUtil.renderJson(response, RespBean.success("用户登录成功"));
    }
}