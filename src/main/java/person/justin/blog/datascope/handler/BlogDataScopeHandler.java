package person.justin.blog.datascope.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import person.justin.blog.enums.DataScopeEm;
import person.justin.blog.model.DataScopeModel;
import person.justin.blog.model.LoginUser;

import java.util.Objects;

/**
 * <p>数据权限规则实现
 *
 * @author gym on 2023-02-06 10:02
 */
@RequiredArgsConstructor
public class BlogDataScopeHandler implements DataScopeHandler {

    private ScopeModelHandler scopeModelHandler;

    @Override
    public String sqlCondition(String mapperId, DataScopeModel dataScope, LoginUser user, String originalSql) {

        String resourceCode = dataScope.getResourceCode();

        DataScopeModel dataScopeDb = scopeModelHandler.getDataScopeByMapper(mapperId, user.getUser().getId());
        if (ObjectUtil.isNull(dataScopeDb) && StrUtil.isNotBlank(resourceCode)) {
            dataScopeDb = scopeModelHandler.getDataScope(resourceCode);
        }

        dataScope = ObjectUtil.isNotNull(dataScopeDb) ? dataScopeDb : dataScope;
        Integer scopeType = Objects.requireNonNull(dataScope).getScopeType();
        DataScopeEm dataScopeEm = DataScopeEm.getDataScopeType(scopeType);

        return null;
    }
}
