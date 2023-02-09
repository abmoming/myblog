package person.justin.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>数据权限实体类
 *
 * @author gym on 2023-02-05 16:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_data_scope")
public class DataScope extends BasePojo {

    private static final long serialVersionUID = 842757068772056858L;
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 菜单主键
     */
    private Long menuId;

    /**
     * 资源编号
     */
    private String resourceCode;

    /**
     * 数据权限名称
     */
    private String scopeName;

    /**
     * 数据权限字段（供筛选数据使用）
     */
    private String scopeColumn;

    /**
     * 数据权限可见字段
     */
    private String visibleField;

    /**
     * 数据权限全限定类名方法
     */
    private String scopeClass;

    /**
     * 数据权限类型
     */
    private Integer scopeType;

    /**
     * 数据权限规则值（供自定义规则类型使用）
     */
    private String scopeValue;

    /**
     * 数据权限备注
     */
    private String remark;

    /**
     * 创建部门
     */
    private Long createDept;
}