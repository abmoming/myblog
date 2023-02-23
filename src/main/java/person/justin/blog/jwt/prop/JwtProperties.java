package person.justin.blog.jwt.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import person.justin.blog.constant.JwtConstant;

/**
 * <p>Jwt属性类
 *
 * @author gym on 2023-02-16 11:39
 */
@Data
@ConfigurationProperties("blog.token")
public class JwtProperties {

    /**
     * token是否有状态
     */
    private Boolean state = Boolean.FALSE;

    /**
     * 是否只可同时一人在线
     */
    private Boolean single = Boolean.FALSE;

    /**
     * token签名
     */
    private String signKey = JwtConstant.DEFAULT_SECRET_KEY;

    public String getSignKey() {

        if (signKey.length() < JwtConstant.SECRET_KEY_LENGTH) {
            return JwtConstant.DEFAULT_SECRET_KEY;
        }

        return this.signKey;
    }
}