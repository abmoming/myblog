package person.justin.blog.security.handler;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import person.justin.blog.model.RespBean;
import person.justin.blog.utils.HttpResponseUtil;
import person.justin.blog.utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>自定义表单登录失败返回异常信息
 *
 * @author gym on 2023-01-11 23:28
 */
@Component
public class BlogAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException ex) throws IOException, ServletException {

        RespBean<Object> rb = RespBean.fail("登录失败!");
        if (ex instanceof LockedException) {
            rb.setMsg("账户被锁定，请联系管理员!");
        } else if (ex instanceof DisabledException) {
            rb.setMsg("账户被禁用，请联系管理员!");
        } else if (ex instanceof CredentialsExpiredException) {
            rb.setMsg("密码已过期，请联系管理员!");
        } else if (ex instanceof AccountExpiredException) {
            rb.setMsg("账户已过期，请联系管理员!");
        } else if (ex instanceof BadCredentialsException) {
            rb.setMsg("用户名或密码输入错误，请重新输入!");
        }
        WebUtil.renderJson(resp, rb);
        // HttpResponseUtil.responseData(resp, rb);
    }
}