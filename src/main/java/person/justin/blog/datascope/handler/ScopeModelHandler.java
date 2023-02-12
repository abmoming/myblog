package person.justin.blog.datascope.handler;

import person.justin.blog.datascope.model.DataScopeModel;

/**
 * <p>数据权限模型统一接口
 *
 * @author gym on 2023-02-10 10:28
 */
public interface ScopeModelHandler {

    /**
     * 通过mapper bean获取DataScope
     *
     * @param mapperId mapper.xml唯一ID(包含方法名称)
     * @param userId   用户ID
     * @return DataScopeModel
     */
    DataScopeModel getDataScopeByMapper(String mapperId, long userId);

    /**
     * 通过资源编号获取数据权限模型
     *
     * @param resourceCode 资源编号
     * @return DataScopeModel
     */
    DataScopeModel getDataScope(String resourceCode);
}