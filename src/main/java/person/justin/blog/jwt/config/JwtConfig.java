package person.justin.blog.jwt.config;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import person.justin.blog.jwt.prop.JwtProperties;

/**
 * <p>jwt配置类
 *
 * @author gym on 2023-02-16 12:06
 */
@SpringBootConfiguration(proxyBeanMethods = false)
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {
        // TODO 初始化工具的属性
    }
}