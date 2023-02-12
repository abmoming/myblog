package person.justin.blog.datascope.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import person.justin.blog.datascope.handler.BlogDataScopeHandler;
import person.justin.blog.datascope.handler.BlogScopeModelHandler;
import person.justin.blog.datascope.handler.DataScopeHandler;
import person.justin.blog.datascope.handler.ScopeModelHandler;
import person.justin.blog.datascope.inteceport.DataScopeInterceptor;
import person.justin.blog.datascope.props.DataScopeProperties;

/**
 * <p>数据权限配置类
 *
 * @author gym on 2023-02-03 11:08
 */
@SpringBootConfiguration(proxyBeanMethods = false)
@EnableConfigurationProperties(DataScopeProperties.class)
public class DataScopeConfig {

    @Bean
    @ConditionalOnMissingBean(ScopeModelHandler.class)
    public ScopeModelHandler scopeModelHandler() {
        return new BlogScopeModelHandler();
    }

    @Bean
    @ConditionalOnBean(ScopeModelHandler.class)
    @ConditionalOnMissingBean(DataScopeHandler.class)
    public DataScopeHandler dataScopeHandler(ScopeModelHandler scopeModelHandler) {
        return new BlogDataScopeHandler(scopeModelHandler);
    }

    @Bean
    @ConditionalOnBean(DataScopeHandler.class)
    @ConditionalOnMissingBean(DataScopeInterceptor.class)
    public DataScopeInterceptor dataScopeInterceptor(DataScopeHandler dataScopeHandler, DataScopeProperties dataScopeProperties) {
        return new DataScopeInterceptor(dataScopeHandler, dataScopeProperties);
    }
}