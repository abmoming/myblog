package person.justin.blog.demo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * @author gym on 2023-02-01 15:01
 */
@Configuration
@EnableConfigurationProperties(TestConfig.class)
public class TestConfigurationProperties {
}
