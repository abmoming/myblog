package person.justin.blog.datascope.handler;

import person.justin.blog.model.DataScopeModel;
import person.justin.blog.model.LoginUser;

/**
 * <p>数据权限规则实现
 *
 * @author gym on 2023-02-06 10:02
 */
public class BlogDataScopeHandler implements DataScopeHandler{

    @Override
    public String sqlCondition(String mapperId, DataScopeModel dataScope, LoginUser loginUser, String originalSql) {

        // 1.通过msId和用户ID查出用户对应的数据权限
        // 2.判断查出来的数据权限为空且dataScope的资源编号不为空
        // 3.





        return null;
    }
}
