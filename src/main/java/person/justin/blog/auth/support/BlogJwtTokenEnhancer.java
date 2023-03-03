package person.justin.blog.auth.support;

import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import person.justin.blog.jwt.prop.JwtProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>jwt增强器
 *
 * @author gym on 2023-02-16 12:26
 */
@SuppressWarnings({"deprecation"})
@RequiredArgsConstructor
public class BlogJwtTokenEnhancer implements TokenEnhancer {

    private final JwtProperties jwtProperties;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        HashMap<String, Object> info = new HashMap<>();
        HashMap<String, Object> infos = new HashMap<>();
        info.put("customer", "自定义内容");
        info.put("dept_id", "部门id");
        info.put("permiss_id", "资源集");
        infos.put("infos", info);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(infos);

        return ObjectUtil.isNotNull(jwtAccessTokenConverter) ? jwtAccessTokenConverter.enhance(accessToken, authentication) : accessToken;
    }
}