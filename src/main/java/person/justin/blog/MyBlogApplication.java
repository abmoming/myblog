package person.justin.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import person.justin.blog.auth.filter.AuthFilter;

/**
 * <p>启动类
 *
 * @author gym on 2023-01-07-17:38
 */
@MapperScan(basePackages = {"person.justin.blog.mapper"})
@SpringBootApplication
public class MyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }
}
