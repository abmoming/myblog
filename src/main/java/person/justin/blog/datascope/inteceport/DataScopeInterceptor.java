package person.justin.blog.datascope.inteceport;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import person.justin.blog.annotation.DataAuth;
import person.justin.blog.datascope.handler.DataScopeHandler;
import person.justin.blog.datascope.props.DataScopeProperties;
import person.justin.blog.model.DataScopeModel;
import person.justin.blog.model.LoginUser;
import person.justin.blog.mybatis.interceptor.QueryInterceptor;
import person.justin.blog.utils.AuthUtil;
import person.justin.blog.utils.SpringUtil;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>数据权限拦截器
 *
 * @author gym on 2023-02-03 10:17
 */
@RequiredArgsConstructor
public class DataScopeInterceptor implements QueryInterceptor {

    private final DataScopeHandler dataScopeHandler;

    private final DataScopeProperties dataScopeProperties;

    private final ConcurrentMap<String, DataAuth> dataAuthMap = new ConcurrentHashMap<>(8);

    @Override
    public void intercept(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {

        // 目的：拦截查询的sql，进行额外的处理
        // 1.判断数据权限配置文件是否开启数据权限
        // 2.判断sqlCommandType类型是否是查询类型 或者 StatementType是CALLABLE类型
        // 3.判断mapper.xml文件的方法名称是否包含page、Page、list、List关键字，不包含则return

        if (!dataScopeProperties.getEnabled()
                || ms.getSqlCommandType() != SqlCommandType.SELECT
                || ms.getStatementType() == StatementType.CALLABLE) {
            return;
        }

        LoginUser user = AuthUtil.getUser();
        if (Objects.isNull(user)) {
            return;
        }

        // 查找mapper接口方法的DataAuth注解
        DataAuth dataAuth = findDataAuthAnnotation(ms);
        MapperModel mapperModel = MapperModel.getInstance(ms.getId());

        // 注解DataAuth为空且不匹配关键字以及是排除的mapper
        boolean mapperSkip = dataScopeProperties.getMapperKey().stream().noneMatch(mapperModel.getMethodName()::contains)
                || dataScopeProperties.getMapperExclude().stream().anyMatch(mapperModel.getMapperName()::contains);
        if (Objects.isNull(dataAuth) && mapperSkip) {
            return;
        }

        DataScopeModel dataScope = initDataScopeModel(dataAuth);
        // 数据权限规则处理
        dataScopeHandler.sqlCondition(ms.getId(), dataScope, user, boundSql.getSql());


    }

    @Data
    static class MapperModel {
        /**
         * mapper.xml定义的唯一id
         */
        private String mapperId;
        /**
         * 全路径类名称
         */
        private String className;
        /**
         * 方法名称
         */
        private String methodName;
        /**
         * mapper Bean名称
         */
        private String mapperName;
        /**
         * mapper Bean对象
         */
        private Object mapper;

        /**
         * 获取mapperModel实例对象
         *
         * @return MapperModel
         */
        private static MapperModel getInstance() {
            return new MapperModel();
        }

        /**
         * 通过mapperId初始化MapperModel
         *
         * @param mapperId mapper.xml定义的唯一id
         * @return MapperModel
         */
        private static MapperModel getInstance(String mapperId) {

            if (StrUtil.isBlank(mapperId)) {
                return getInstance();
            }

            String className = mapperId.substring(0, mapperId.lastIndexOf(StringPool.DOT));
            String methodName = mapperId.substring(mapperId.lastIndexOf(StringPool.DOT) + 1);
            String mapperName = StrUtil.lowerFirst(ClassUtils.getShortName(className));
            Object mapper = SpringUtil.getBean(mapperName);

            MapperModel mapperModel = getInstance();
            mapperModel.setMapperId(mapperId);
            mapperModel.setClassName(className);
            mapperModel.setMethodName(methodName);
            mapperModel.setMapperName(mapperName);
            mapperModel.setMapper(mapper);
            return mapperModel;
        }
    }

    /**
     * 查找DataAuth注解
     *
     * @param mappedStatement sql映射类
     * @return DataAuth
     */
    private DataAuth findDataAuthAnnotation(MappedStatement mappedStatement) {
        String mapperId = mappedStatement.getId();
        return dataAuthMap.computeIfAbsent(mapperId, (key) -> {
            MapperModel mapperModel = MapperModel.getInstance(key);
            // 获取该bean的所有接口
            Class<?>[] interfaces = ClassUtils.getAllInterfaces(mapperModel.getMapper());
            for (Class<?> mapperInterface : interfaces) {
                for (Method method : mapperInterface.getDeclaredMethods()) {
                    if (Objects.equals(mapperModel.getMethodName(), method.getName()) && method.isAnnotationPresent(DataAuth.class)) {
                        // return AnnotationUtils.getAnnotation(method, DataAuth.class);
                        return method.getAnnotation(DataAuth.class);
                    }
                }
            }
            return null;
        });
    }

    /**
     * 通过@DataAuth注解参数初始化DataScopeModel
     *
     * @param dataAuth 数据权限定义注解
     * @return DataScopeModel
     */
    private DataScopeModel initDataScopeModel(DataAuth dataAuth) {

        if (Objects.isNull(dataAuth)) {
            return new DataScopeModel();
        }

        DataScopeModel dataScopeModel = new DataScopeModel();
        dataScopeModel.setResourceCode(dataAuth.code());
        dataScopeModel.setScopeColumn(dataAuth.column());
        dataScopeModel.setScopeType(dataAuth.scopeType().getType());
        dataScopeModel.setVisibleField(dataAuth.visibleField());
        dataScopeModel.setScopeValue(dataAuth.value());
        return dataScopeModel;
    }
}