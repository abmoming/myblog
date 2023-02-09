package person.justin.blog.mybatis.plugins;

import cn.hutool.core.util.ArrayUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import person.justin.blog.mybatis.interceptor.QueryInterceptor;

/**
 * <p>查询拦截器执行器
 * 目的：抽取此方法是为了后期方便同步更新 {@link BlogPaginationInnerInterceptor}
 *
 * @author gym on 2023-02-03 15:14
 */
@SuppressWarnings({"rawtypes"})
public class QueryInterceptorExecutor {


    /**
     * 执行查询拦截器
     *
     * @param queryInterceptors QueryInterceptor实例集
     * @param executor          执行器(mybatis中执行查询的主要代码)
     * @param ms                sql映射
     * @param parameter         parameter
     * @param rowBounds         将所有符合条件的数据全都查询到内存中，然后在内存中对数据进行分页，若数据量大，千万别使用RowBounds
     * @param resultHandler     resultHandler
     * @param boundSql          boundSql
     */
    public static void execute(QueryInterceptor[] queryInterceptors, Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        if (ArrayUtil.isEmpty(queryInterceptors)) {
            return;
        }
        for (QueryInterceptor queryInterceptor : queryInterceptors) {
            queryInterceptor.intercept(executor, ms, parameter, rowBounds, resultHandler, boundSql);
        }
    }
}