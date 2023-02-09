package person.justin.blog.mybatis.config;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import person.justin.blog.mybatis.interceptor.QueryInterceptor;
import person.justin.blog.mybatis.plugins.BlogPaginationInnerInterceptor;
import person.justin.blog.mybatis.props.MybatisPlusProperties;

/**
 * <p>mybatis-plus配置类
 *
 * @author gym on 2023-02-02 20:15
 */
@SpringBootConfiguration(proxyBeanMethods = false)
@EnableConfigurationProperties(MybatisPlusProperties.class)
public class MybatisPlusConfiguration {

    @Bean
    @ConditionalOnMissingBean(MybatisPlusInterceptor.class)
    public MybatisPlusInterceptor mybatisPlusInterceptor(ObjectProvider<QueryInterceptor[]> queryInterceptorObjectProvider,
                                                         MybatisPlusProperties mybatisPlusProperties) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        BlogPaginationInnerInterceptor paginationInnerInterceptor = new BlogPaginationInnerInterceptor();
        QueryInterceptor[] queryInterceptors = queryInterceptorObjectProvider.getIfAvailable();
        if (ArrayUtil.isNotEmpty(queryInterceptors)) {
            // ObjectProvider默认就是根据Ordered或者@Order优先级加载的，无需AnnotationAwareOrderComparator.sort()重新排序
            paginationInnerInterceptor.setQueryInterceptors(queryInterceptors);
        }
        paginationInnerInterceptor.setOverflow(mybatisPlusProperties.getOverflow());
        paginationInnerInterceptor.setMaxLimit(mybatisPlusProperties.getMaxLimit());
        paginationInnerInterceptor.setOptimizeJoin(mybatisPlusProperties.getOptimizeJoin());
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}
