package person.justin.blog.demo.Import;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import person.justin.blog.demo.color.ColorFactoryBean;

/**
 * <p>ImportAnnotationTest
 *
 * @author gym on 2023-02-01 15:36
 */
// @Configuration
@Component
@Import({MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class ImportAnnotationTest {

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}