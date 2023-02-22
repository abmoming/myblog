package person.justin.blog.auth.granter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * <p>验证码授权类型
 *
 * @author gym on 2023-02-14 17:14
 */
@SuppressWarnings({"deprecation","all"})
public class CaptchaTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "captcha";

    protected CaptchaTokenGranter(AuthenticationManager authenticationManager,
                                  AuthorizationServerTokenServices tokenServices,
                                  ClientDetailsService clientDetailsService,
                                  OAuth2RequestFactory requestFactory) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    protected CaptchaTokenGranter(AuthenticationManager authenticationManager,
                                  AuthorizationServerTokenServices tokenServices,
                                  ClientDetailsService clientDetailsService,
                                  OAuth2RequestFactory requestFactory,
                                  String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        // return super.getOAuth2Authentication(client, tokenRequest);





        return null;
    }
}