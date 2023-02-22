package person.justin.blog.auth.service;

import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * <p>客户端业务实现层
 *
 * @author gym on 2023-02-16 21:38
 */
public class BlogClientDetailServiceImpl extends JdbcClientDetailsService {

    public BlogClientDetailServiceImpl(DataSource dataSource) {
        super(dataSource);
    }
}