package person.justin.blog.advice;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import person.justin.blog.annotation.WebResponseExclude;
import person.justin.blog.constant.CommonConstant;
import person.justin.blog.model.RespBean;

import java.util.Map;
import java.util.Objects;

/**
 * <p>应用响应建议
 * 在这里做全局响应处理
 *
 * @author gym on 2023-01-16 15:38
 */
@ControllerAdvice
public class WebResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 是否支持响应处理
     * 带有WebResponseExclude注解的不做处理
     *
     * @param returnType    方法类型
     * @param converterType http消息转换器类型
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.hasMethodAnnotation(WebResponseExclude.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof RespBean) {
            return body;
        }

        if (Objects.equals(BasicErrorController.class, returnType.getExecutable().getDeclaringClass())) {
            if (body instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) body;
                return RespBean.fail((Integer) map.get(CommonConstant.STATUS), (String) map.get(CommonConstant.MESSAGE));
            }
            return body;
        }

        return RespBean.success(body);
    }
}
