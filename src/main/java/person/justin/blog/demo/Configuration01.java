package person.justin.blog.demo;

import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * @author gym on 2023-02-01 09:46
 */
@Configuration
public class Configuration01 {

    public Configuration01() {
        System.out.println("被扫描到的bean");
    }
}
