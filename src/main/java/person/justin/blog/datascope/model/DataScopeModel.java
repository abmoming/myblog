package person.justin.blog.datascope.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import person.justin.blog.constant.DataScopeConstant;
import person.justin.blog.enums.DataScopeEm;

import java.io.Serializable;

/**
 * <p>数据权限模型
 *
 * @author gym on 2023-02-04 11:18
 */
@Data
@NoArgsConstructor
public class DataScopeModel implements Serializable {


    public DataScopeModel(Boolean searched) {
        this.searched = searched;
    }

    /**
     * 是否已查询
     */
    private Boolean searched = Boolean.FALSE;

    /**
     * 资源编号
     */
    private String resourceCode;

    /**
     * 数据权限字段
     */
    private String scopeColumn = DataScopeConstant.DEFAULT_COLUMN;

    /**
     * 数据权限类型
     */
    private Integer scopeType = DataScopeEm.ALL.getType();

    /**
     * 可见字段
     */
    private String visibleField;

    /**
     * 数据权限规则值
     */
    private String scopeValue;
}