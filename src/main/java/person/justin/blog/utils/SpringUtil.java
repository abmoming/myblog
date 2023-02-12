package person.justin.blog.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * <p>
 *
 * @author gym on 2023-02-06 20:43
 */
@Component
public class SpringUtil implements BeanFactoryPostProcessor, ApplicationContextAware {

    /**
     * "@PostConstruct"注解标记的类中，由于ApplicationContext还未加载，导致空指针<br>
     * 因此实现BeanFactoryPostProcessor注入ConfigurableListableBeanFactory实现bean的操作
     */
    private static ConfigurableListableBeanFactory beanFactory;

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringUtil.beanFactory = configurableListableBeanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 获取bean工厂
     *
     * @return ListableBeanFactory
     */
    public static ListableBeanFactory getBeanFactory() {
        return Objects.isNull(beanFactory) ? applicationContext : beanFactory;
    }

    /**
     * 通过name获取Bean
     *
     * @param beanName Bean名称
     * @param <T>      Bean类型
     * @return Bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) getBeanFactory().getBean(beanName);
    }

    /**
     * 通过class获取Bean
     *
     * @param clazz 类
     * @param <T>   Bean类型
     * @return Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getBeanFactory().getBean(clazz);
    }
}
