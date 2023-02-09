package person.justin.blog.demo;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * @author gym on 2023-02-01 09:55
 */
@Configuration
@AutoConfigureBefore(Configuration02.class)
public class Configuration03 {

    public Configuration03() {
        System.out.println("初始化Configuration03");
    }
}
