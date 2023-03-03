package person.justin.blog.auth.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>授权属性
 *
 * @author gym on 2023-02-27 13:54
 */
@Data
@ConfigurationProperties("blog.secure")
public class AuthProperties {

    /**
     * 放行API集合
     */
    private List<String> skipUrl = new ArrayList<>();
}