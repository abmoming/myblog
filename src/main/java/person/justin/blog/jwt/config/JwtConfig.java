package person.justin.blog.jwt.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import person.justin.blog.jwt.prop.JwtProperties;
import person.justin.blog.redis.BlogRedis;
import person.justin.blog.utils.JwtUtil;

/**
 * <p>jwt配置类
 *
 * @author gym on 2023-02-16 12:06
 */
@AllArgsConstructor
@SpringBootConfiguration(proxyBeanMethods = false)
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig implements SmartInitializingSingleton {

    private final JwtProperties jwtProperties;

    private final BlogRedis blogRedis;

    @Override
    public void afterSingletonsInstantiated() {
        // TODO 初始化工具的属性，这里可以单独给jwt配置redis的序列化模式
        JwtUtil.setJwtProperties(jwtProperties);
        JwtUtil.setBlogRedis(blogRedis);
    }
}