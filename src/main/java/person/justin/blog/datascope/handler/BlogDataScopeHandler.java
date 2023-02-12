package person.justin.blog.datascope.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.RequiredArgsConstructor;
import person.justin.blog.datascope.model.DataScopeModel;
import person.justin.blog.enums.DataScopeEm;
import person.justin.blog.model.LoginUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>数据权限规则实现
 *
 * @author gym on 2023-02-06 10:02
 */
@RequiredArgsConstructor
public class BlogDataScopeHandler implements DataScopeHandler {

    private final ScopeModelHandler scopeModelHandler;

    @Override
    public String sqlCondition(String mapperId, DataScopeModel dataScope, LoginUser user, String originalSql) {

        String whereSql = " where scope.{} in ({})";
        String resourceCode = dataScope.getResourceCode();
        List<Long> ids = new ArrayList<>();

        DataScopeModel dataScopeDb = scopeModelHandler.getDataScopeByMapper(mapperId, user.getUser().getId());
        if (ObjectUtil.isNull(dataScopeDb) && StrUtil.isNotBlank(resourceCode)) {
            dataScopeDb = scopeModelHandler.getDataScope(resourceCode);
        }

        dataScope = ObjectUtil.isNotNull(dataScopeDb) ? dataScopeDb : dataScope;
        Integer scopeType = Objects.requireNonNull(dataScope).getScopeType();
        DataScopeEm dataScopeEm = DataScopeEm.getDataScopeType(scopeType);
        switch (dataScopeEm) {
            case OWN:
                ids.add(user.getUser().getId());
                break;
            case OWN_DEPT:
                // TODO 部门数据过滤
                break;
            case OWN_DEPT_CHILD:
                // TODO 部门和子部门的数据过滤
                break;
            case CUSTOM:
                // TODO 自定义
                break;
            default:
                return null;
        }

        // select {} from ({}) scope where scope.{} in ({});
        return StrUtil.format("select {} from ({}) scope".concat(whereSql)
                , StrUtil.isEmpty(dataScope.getVisibleField()) ? StringPool.STAR : dataScope.getVisibleField()
                , originalSql
                , dataScope.getScopeColumn()
                , StrUtil.join(StringPool.COMMA, ids));
    }
}