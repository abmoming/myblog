package person.justin.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.util.Arrays;
import java.util.Objects;

/**
 * <p>数据权限枚举
 *
 * @author gym on 2023-02-04 11:27
 */
@Getter
@AllArgsConstructor
public enum DataScopeEm {

    // TODO 数据权限枚举类需要定义未知的情况吗？

    /**
     * 全部
     */
    ALL(1, "全部可见"),
    /**
     * 本人可见
     */
    OWN(2, "本人可见"),
    /**
     * 所在部门可见
     */
    OWN_DEPT(3, "所在部门可见"),
    /**
     * 所在部门以及子部门可见
     */
    OWN_DEPT_CHILD(4, "所在部门以及子部门可见"),
    /**
     * 自定义
     */
    CUSTOM(5, "自定义");

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 描述
     */
    private final String description;

    /**
     * 通过类型获取数据权限
     *
     * @param type 类型
     * @return DataScopeEm
     */
    public static DataScopeEm getDataScopeType(Integer type) {
        for (DataScopeEm val : DataScopeEm.values()) {
            if (Objects.equals(val.getType(), type)) {
                return val;
            }
        }
        return null;
    }
}