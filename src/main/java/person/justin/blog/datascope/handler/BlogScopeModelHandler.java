package person.justin.blog.datascope.handler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.NonNull;
import person.justin.blog.constant.DataScopeConstant;
import person.justin.blog.datascope.model.DataScopeModel;
import person.justin.blog.utils.BlogJdbcTemplateUtil;

import java.util.List;

/**
 * <p>数据权限模型统一接口实现
 *
 * @author gym on 2023-02-10 10:33
 */
public class BlogScopeModelHandler implements ScopeModelHandler {

    private static final DataScopeModel SEARCHED_DATA_SCOPE_MODEL = new DataScopeModel(Boolean.TRUE);

    @Override
    public DataScopeModel getDataScopeByMapper(String mapperId, long userId) {

        // 这里先不用缓存
        DataScopeModel dataScopeModel = null;
        if (ObjectUtil.isNull(dataScopeModel) || !dataScopeModel.getSearched()) {
            List<DataScopeModel> dataScopeModels = BlogJdbcTemplateUtil.select(DataScopeConstant.DATA_BY_MAPPER, DataScopeModel.class, mapperId, userId);
            if (CollectionUtil.isNotEmpty(dataScopeModels)) {
                dataScopeModel = dataScopeModels.iterator().next();
                dataScopeModel.setSearched(Boolean.TRUE);
            } else {
                dataScopeModel = SEARCHED_DATA_SCOPE_MODEL;
            }
        }
        return dataScopeModel;
    }

    @Override
    public DataScopeModel getDataScope(@NonNull String resourceCode) {

        // 这里先不用缓存
        DataScopeModel dataScopeModel = null;

        if (ObjectUtil.isNull(dataScopeModel) || !dataScopeModel.getSearched()) {

            List<DataScopeModel> dataScopeModels = BlogJdbcTemplateUtil.select(DataScopeConstant.DATA_BY_RESOURCE_CODE, DataScopeModel.class, resourceCode);
            if (CollectionUtil.isNotEmpty(dataScopeModels)) {
                dataScopeModel = dataScopeModels.iterator().next();
                dataScopeModel.setSearched(Boolean.TRUE);
            } else {
                dataScopeModel = SEARCHED_DATA_SCOPE_MODEL;
            }
        }

        return dataScopeModel;
    }
}