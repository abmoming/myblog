package person.justin.blog.datascope.handler;

import person.justin.blog.datascope.model.DataScopeModel;
import person.justin.blog.model.LoginUser;

/**
 * <p>数据权限规则
 *
 * @author gym on 2023-02-04 11:07
 */
public interface DataScopeHandler {

    /**
     * sql规则
     *
     * @param mapperId    mapperId
     * @param dataScope   数据权限模型
     * @param user   登录用户
     * @param originalSql 源sql
     * @return String
     */
    String sqlCondition(String mapperId, DataScopeModel dataScope, LoginUser user, String originalSql);
}