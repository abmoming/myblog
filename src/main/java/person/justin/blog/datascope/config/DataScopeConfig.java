package person.justin.blog.datascope.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import person.justin.blog.datascope.handler.BlogDataScopeHandler;
import person.justin.blog.datascope.handler.DataScopeHandler;
import person.justin.blog.datascope.inteceport.DataScopeInterceptor;
import person.justin.blog.datascope.props.DataScopeProperties;

/**
 * <p>
 *
 * @author gym on 2023-02-03 11:08
 */
@SpringBootConfiguration
@EnableConfigurationProperties(DataScopeProperties.class)
public class DataScopeConfig {

    @Bean
    public DataScopeHandler dataScopeHandler() {
        return new BlogDataScopeHandler();
    }

    @Bean
    @ConditionalOnMissingBean(DataScopeInterceptor.class)
    public DataScopeInterceptor dataScopeInterceptor(DataScopeHandler dataScopeHandler, DataScopeProperties dataScopeProperties) {
        return new DataScopeInterceptor(dataScopeHandler, dataScopeProperties);
    }
}