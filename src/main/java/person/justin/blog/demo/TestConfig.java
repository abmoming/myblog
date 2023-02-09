package person.justin.blog.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author gym on 2023-02-01 15:01
 */
// @Component
@Data
@ConfigurationProperties(prefix = "blog.b")
public class TestConfig {

    private Long id;
    private String name;
}
