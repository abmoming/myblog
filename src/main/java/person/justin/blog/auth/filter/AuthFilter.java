package person.justin.blog.auth.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;
import person.justin.blog.auth.props.AuthProperties;
import person.justin.blog.auth.provider.AuthProvider;
import person.justin.blog.model.RespBean;
import person.justin.blog.utils.JwtUtil;
import person.justin.blog.utils.WebUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <p>鉴权认证过滤器
 *
 * @author gym on 2023-02-24 10:39
 */
@Component
@AllArgsConstructor
public class AuthFilter extends GenericFilterBean implements Ordered {

    private static final long serialVersionUID = 1L;

    private final AuthProperties authProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 1.判断是否跳过的路径
        // 2.判断请求头、请求体是否包含token标识
        // 3.解析token是否有效
        // 4.判断token是否有状态的（这一步还没做）
        // 5.一个是AuthProperties提供的，另一个是配置文件可配置的

        HttpServletRequest req = ((HttpServletRequest) servletRequest);
        HttpServletResponse resp = ((HttpServletResponse) servletResponse);
        String headerToken = req.getHeader(AuthProvider.AUTH_KEY);
        String paramToken = req.getParameter(AuthProvider.AUTH_KEY);
        String uri = req.getRequestURI();

        if (isSkip(uri)) {
            filterChain.doFilter(req, resp);
            return;
        }

        if (StrUtil.isBlank(headerToken) && StrUtil.isBlank(paramToken)) {
            noAuth(resp, "缺失令牌，鉴权失败");
            return;
        }

        String auth = StrUtil.isNotBlank(headerToken) ? headerToken : paramToken;
        String token = JwtUtil.getToken(auth);
        Claims claims = JwtUtil.parseJwt(token);
        if (StrUtil.isEmpty(token) || ObjectUtil.isNull(claims)) {
            noAuth(resp, "请求未授权");
            return;
        }

        // 判断是否有状态


        filterChain.doFilter(req, resp);
    }

    @Override
    public int getOrder() {
        return -100;
    }

    /**
     * 响应未授权的信息
     *
     * @param resp    response
     * @param message 信息内容
     */
    private void noAuth(HttpServletResponse resp, String message) {
        WebUtil.renderJson(resp, RespBean.fail(HttpStatus.UNAUTHORIZED.value(), message));
    }

    /**
     * 是否跳转的路径
     *
     * @param uri uri
     * @return boolean
     */
    private boolean isSkip(String uri) {

        // 将默认和配置文件的跳转路径合并到同一个集合中，进行匹配跳过处理
        List<String> defaultSkipUris = unionAll(AuthProvider.getDefaultSkipUri(), authProperties.getSkipUrl());
        return defaultSkipUris.stream().anyMatch(pattern -> antPathMatcher.match(pattern, uri));
    }

    /**
     * 合并list
     *
     * @param coll1 集合1
     * @param coll2 集合2
     * @return List<T>
     */
    private <T> List<T> unionAll(Collection<T> coll1, Collection<T> coll2) {

        if (CollectionUtil.isEmpty(coll1) && CollectionUtil.isEmpty(coll2)) {
            return Collections.emptyList();
        }
        // 根据size创建，防止多次扩容
        int totalSize = 0;
        totalSize += size(coll1);
        totalSize += size(coll2);
        final List<T> res = new ArrayList<>(totalSize);
        if (CollectionUtil.isNotEmpty(coll1)) {
            res.addAll(coll1);
        }
        if (CollectionUtil.isNotEmpty(coll2)) {
            res.addAll(coll2);
        }
        return res;
    }

    /**
     * 计算集合size
     *
     * @param coll 集合
     * @return int集合大小
     */
    private <T> int size(Collection<T> coll) {

        if (CollectionUtil.isEmpty(coll)) {
            return 0;
        }
        return coll.size();
    }
}