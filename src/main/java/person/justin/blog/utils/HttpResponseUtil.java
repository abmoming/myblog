package person.justin.blog.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import person.justin.blog.constant.CommonConstant;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * <p>http 响应工具类
 *
 * @author gym on 2023-01-16 11:11
 */
public class HttpResponseUtil {

    /**
     * 输出响应数据
     *
     * @param resp    http响应
     * @param content 响应内容
     */
    @SneakyThrows
    public static void responseData(HttpServletResponse resp, Object content) {
        resp.setContentType(CommonConstant.APPLICATION_JSON_UTF8);
        PrintWriter writer = resp.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(content));
        writer.flush();
        writer.close();
    }
}