package person.justin.blog.mybatis.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.core.Ordered;

/**
 * <p>查询拦截器
 *
 * @author gym on 2023-02-03 10:13
 */
@SuppressWarnings("rawtypes")
public interface QueryInterceptor extends Ordered {

    /**
     * 执行查询拦截器
     *
     * @param executor      执行器(mybatis中执行查询的主要代码)
     * @param ms            sql映射类
     * @param parameter     parameter
     * @param rowBounds     将所有符合条件的数据全都查询到内存中，然后在内存中对数据进行分页，若数据量大，千万别使用RowBounds
     * @param resultHandler resultHandler
     * @param boundSql      boundSql
     */
    void intercept(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql);

    /**
     * 拦截器执行的优先级
     *
     * @return int
     */
    @Override
    default int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
