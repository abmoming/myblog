package person.justin.blog.demo;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import person.justin.blog.mybatis.interceptor.QueryInterceptor;
import person.justin.blog.mybatis.props.MybatisPlusProperties;

import java.util.Arrays;

/**
 * <p>
 *
 * @author gym on 2023-02-03 10:23
 */
// @EnableConfigurationProperties(MybatisPlusProperties.class)
// @SpringBootConfiguration(proxyBeanMethods = false)
@Component
public class E {

    private final F f;

    // @Bean
    // public F f(ObjectProvider<QueryInterceptor[]> queryInterceptors, MybatisPlusProperties mybatisPlusProperties) {
    //     System.out.println("queryInterceptors--->" + Arrays.toString(queryInterceptors.getIfAvailable()));
    //     System.out.println(mybatisPlusProperties);
    //     return new F();
    // }

    public E(F f) {
        this.f = f;
    }

    public void demo() {
        f.demo();
    }


}
