package person.justin.blog.demo.Import;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>
 *
 * @author gym on 2023-02-01 15:59
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 指定bean定义信息（包括bean的类型、作用域...）
        RootBeanDefinition beanDefinition = new RootBeanDefinition(Bean01.class);
        // 注册一个bean指定bean名称（id）
        registry.registerBeanDefinition("bean01", beanDefinition);
    }
}
