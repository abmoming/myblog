package person.justin.blog.constant;

/**
 * <p>数据权限常量类
 *
 * @author gym on 2023-02-06 16:28
 */
public class DataScopeConstant {

    /**
     * 默认筛选数据条件
     */
    public static final String DEFAULT_COLUMN = "create_dept";

    /**
     * 通过mapper查询数据权限
     */
    // scope_class 可以改为 scope_class_method
    public static final String DATA_BY_MAPPER = "select resource_code, scope_name, visible_field, scope_type, scope_value " +
            "from blog_data_scope where scope_class = ? and " +
            "id in (select scope_id from blog_role_scope where role_id in (select id from blog_user_role where user_id = ?))";

    /**
     * 通过资源编号查询数据权限
     */
    public static final String DATA_BY_RESOURCE_CODE = "select resource_code, scope_name, visible_field, scope_type, scope_value from blog_data_scope " +
            "where resource_code = ?";
}