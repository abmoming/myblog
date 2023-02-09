package person.justin.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * <p>web应用程序的各种实用程序
 *
 * @author gym on 2023-02-07 17:11
 */
@Slf4j
public class WebUtil extends WebUtils {

    /**
     * 获取HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return Objects.isNull(requestAttributes) ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * HttpServletResponse响应字符串数据
     *
     * @param response response
     * @param val      内容
     */
    public static void renderJson(HttpServletResponse response, Object val) {
        renderJson(response, MediaType.APPLICATION_JSON_VALUE, val);
    }

    /**
     * 重载方法 {@link WebUtil#renderJson(HttpServletResponse, Object)}
     */
    public static void renderJson(HttpServletResponse response, String contentType, Object val) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(contentType);
        try (PrintWriter writer = response.getWriter()) {
            writer.append(JsonUtil.toJsonStr(val));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
