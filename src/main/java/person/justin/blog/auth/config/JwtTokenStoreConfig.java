package person.justin.blog.auth.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import person.justin.blog.auth.support.BlogJwtTokenEnhancer;
import person.justin.blog.jwt.prop.JwtProperties;

/**
 * <p>token生成配置类
 *
 * @author gym on 2023-02-15 20:03
 */
@SuppressWarnings({"deprecation"})
@SpringBootConfiguration
@ConditionalOnProperty(prefix = "blog.security.oauth2", name = "store-type", havingValue = "jwt", matchIfMissing = true)
public class JwtTokenStoreConfig {

    /**
     * token存放位置
     */
    @Bean
    public TokenStore tokenStore(JwtProperties jwtProperties) {
        return new JwtTokenStore(jwtAccessTokenConverter(jwtProperties));
    }

    /**
     * 生成jwt
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(JwtProperties jwtProperties) {

        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(jwtProperties.getSignKey());
        return tokenConverter;
    }

    /**
     * 增强jwt(可额外配置信息)
     */
    @Bean
    public TokenEnhancer tokenEnhancer(JwtProperties jwtProperties, JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new BlogJwtTokenEnhancer(jwtProperties, jwtAccessTokenConverter);
    }
}