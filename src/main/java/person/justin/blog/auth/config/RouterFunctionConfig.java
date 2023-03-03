package person.justin.blog.auth.config;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import person.justin.blog.auth.props.AuthProperties;
import reactor.core.publisher.Mono;


/**
 * <p>路由配置信息
 *
 * @author gym on 2023-02-27 13:58
 */
@SpringBootConfiguration(proxyBeanMethods = false)
@EnableConfigurationProperties(AuthProperties.class)
public class RouterFunctionConfig {

    private static final String ALLOWED_HEADERS = "X-Requested-With, Blog-Auth, Content-Type, Authorization, X-XSRF-TOKEN";

    private static final String ALLOWED_METHODS = "GET, POST, PUT, DELETE, OPTIONS, HEAD";

    private static final String ALLOWED_ORIGIN = "*";

    private static final String ALLOWED_EXPOSE = "*";

    private static final String MAX_AGE = "18000L";

    @Bean
    public WebFilter webFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            ServerHttpRequest req = exchange.getRequest();
            if (CorsUtils.isCorsRequest(req)) {
                ServerHttpResponse resp = exchange.getResponse();
                HttpHeaders headers = resp.getHeaders();
                headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
                headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
                headers.add("Access-Control-Max-Age", MAX_AGE);
                headers.add("Access-Control-Allow-Credentials", "true");
                if (ObjectUtil.equals(req.getMethod(), HttpMethod.OPTIONS)) {
                    resp.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(exchange);
        };
    }
}