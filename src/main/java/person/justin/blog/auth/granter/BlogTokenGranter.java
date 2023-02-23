package person.justin.blog.auth.granter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import person.justin.blog.redis.BlogRedis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>自定义拓展tokenGranter
 *
 * @author gym on 2023-02-22 15:13
 */
@SuppressWarnings({"deprecation"})
public class BlogTokenGranter {

    public static TokenGranter getTokenGranter(final AuthenticationManager authenticationManager,
                                               final AuthorizationServerEndpointsConfigurer endpoints,
                                               final BlogRedis blogRedis) {

        TokenGranter defaultTokenGranter = endpoints.getTokenGranter();
        List<TokenGranter> tokenGranters = new ArrayList<>(Collections.singletonList(defaultTokenGranter));
        tokenGranters.add(new CaptchaTokenGranter(authenticationManager,
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(),
                blogRedis));

        return new CompositeTokenGranter(tokenGranters);
    }
}
