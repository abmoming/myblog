package person.justin.blog.auth.granter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import person.justin.blog.constant.TokenConstant;
import person.justin.blog.redis.BlogRedis;
import person.justin.blog.redis.CacheName;
import person.justin.blog.utils.TokenUtil;

import java.util.Map;

/**
 * <p>验证码授权类型
 *
 * @author gym on 2023-02-14 17:14
 */
@SuppressWarnings({"deprecation", "all"})
public class CaptchaTokenGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "captcha";
    private final AuthenticationManager authenticationManager;
    private BlogRedis blogRedis;

    protected CaptchaTokenGranter(AuthenticationManager authenticationManager,
                                  AuthorizationServerTokenServices tokenServices,
                                  ClientDetailsService clientDetailsService,
                                  OAuth2RequestFactory requestFactory, BlogRedis blogRedis) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.blogRedis = blogRedis;
    }

    protected CaptchaTokenGranter(AuthenticationManager authenticationManager,
                                  AuthorizationServerTokenServices tokenServices,
                                  ClientDetailsService clientDetailsService,
                                  OAuth2RequestFactory requestFactory,
                                  String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        // this.authenticationManager = authenticationManager;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        // return super.getOAuth2Authentication(client, tokenRequest);
        // 1.校验传递过来的验证码与缓存中的验证码是否匹配
        // 1.从请求体获取用户名和用户密码
        // 2.交由SpringSecurity校验用户名和密码是否正确
        // 3.判断token是否有效（获取token是否为空或者解析token是否为空）
        // 4.使用SpringSecurity的密码登录模式去校验用户有效性
        // 5.创建oauth请求返回

        Map<String, String> reqParameters = tokenRequest.getRequestParameters();
        String username = reqParameters.get(TokenUtil.USERNAME);
        String password = reqParameters.get(TokenUtil.PASSWORD);
        String captchaKey = reqParameters.get(TokenUtil.CAPTCHA_KEY);
        String captchaCode = reqParameters.get(TokenUtil.CAPTCHA_CODE);
        // 获取并删除，为了防止验证码复用
        String cacheCatpchaCode = blogRedis.getAndDel(CacheName.CAPTCHA_CACHE + captchaKey);
        // 忽略大小写验证
        if (StrUtil.isEmpty(captchaCode) || !StrUtil.equals(captchaCode, cacheCatpchaCode, true)) {
            throw new UserDeniedAuthorizationException(TokenUtil.CAPTCHA_NOT_CORRECT);
        }
        // 使用验证码模式验证用户登录信息
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        // 设置用户详情，这里的详情设置是否跟解析token有关联关系？
        // authentication.set
        try {
            authentication = authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new InvalidGrantException("验证码模式:用户名或密码错误");
        }

        if (ObjectUtil.isNull(authentication) || !authentication.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }

        OAuth2Request oAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(oAuth2Request, authentication);
    }
}