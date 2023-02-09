package person.justin.blog.mybatis.plugins;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.Setter;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import person.justin.blog.mybatis.interceptor.QueryInterceptor;

import java.sql.SQLException;

/**
 * <p>分页内部拦截器
 *
 * @author gym on 2023-02-03 15:23
 */
@Setter
public class BlogPaginationInnerInterceptor extends PaginationInnerInterceptor {

    private QueryInterceptor[] queryInterceptors;

    @Override
    public boolean willDoQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        QueryInterceptorExecutor.execute(queryInterceptors, executor, ms, parameter, rowBounds, resultHandler, boundSql);
        return super.willDoQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
    }
}