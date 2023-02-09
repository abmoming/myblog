package person.justin.blog.demo;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * <p>
 *
 * @author gym on 2023-01-29 17:57
 */
@SpringBootConfiguration
// @ConditionalOnProperty(value = "blog.config.enable", havingValue = "true")
public class DemoConfiguration {
    final int i = 1;

    @Order(Ordered.LOWEST_PRECEDENCE)
    @Bean()
    @ConditionalOnBean(name = "d")
    public A a2() {
        return new C(1L, "c2");
    }

    /*@Bean
    @ConditionalOnMissingBean(A.class)
    public A a1() {
        return new B(1L, "b1");
    }*/

    /*@Bean
    public D d() {
        return new D(1L, "d名称");
    }*/
}