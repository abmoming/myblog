package person.justin.blog.auth.config;

import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import person.justin.blog.auth.granter.BlogTokenGranter;
import person.justin.blog.auth.service.BlogClientDetailServiceImpl;
import person.justin.blog.constant.AuthConstant;
import person.justin.blog.redis.BlogRedis;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>授权服务器
 *
 * @author gym on 2023-02-14 14:04
 */
@Order
@SuppressWarnings({"deprecation"})
@SpringBootConfiguration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class BlogAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final TokenStore tokenStore;

    private final DataSource dataSource;

    private final TokenEnhancer tokenEnhancer;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    private final BlogRedis blogRedis;

    /**
     * 1.配置端点信息
     * 1.1自定义授权类型(新增验证码、第三方登录授权类型)
     * 2.配置客户端信息
     * 3.配置每个接口都得检查token
     *
     * @param endpoints the endpoints configurer
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        TokenGranter tokenGranter = BlogTokenGranter.getTokenGranter(authenticationManager, endpoints, blogRedis);

        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenGranter(tokenGranter);

        if (ObjectUtil.isNotNull(jwtAccessTokenConverter) && ObjectUtil.isNotNull(tokenEnhancer)) {
            TokenEnhancerChain chain = new TokenEnhancerChain();
            chain.setTokenEnhancers(new ArrayList<>(Arrays.asList(jwtAccessTokenConverter, tokenEnhancer)));
            endpoints.tokenEnhancer(chain).accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * 配置客户端详情信息
     *
     * @param clients the client details configurer
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        BlogClientDetailServiceImpl client = new BlogClientDetailServiceImpl(dataSource);
        client.setSelectClientDetailsSql(AuthConstant.DEFAULT_SELECT_STATEMENT);
        client.setFindClientDetailsSql(AuthConstant.DEFAULT_FIND_STATEMENT);
        clients.withClientDetails(client);
    }

    /**
     * 配置安全信息
     *
     * @param security a fluent configurer for security features
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                // 可以了解一下permitAll()和isAuthenticated()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}