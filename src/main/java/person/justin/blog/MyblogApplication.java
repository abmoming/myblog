package person.justin.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>启动类
 *
 * @author gym on 2023-01-07-17:38
 */
//@ComponentScan(basePackages = {""})
@SpringBootApplication(scanBasePackages = {"person.justin.blog"})
public class MyblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogApplication.class, args);
    }

}
