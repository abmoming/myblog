package person.justin.blog.node;

import java.io.Serializable;
import java.util.List;

/**
 * <p>节点类
 *
 * @author gym on 2023-01-19 20:23
 */
public interface Node<T> extends Serializable {

    /**
     * 主键
     *
     * @return Long
     */
    Long getId();

    /**
     * 父级主键
     *
     * @return Long
     */
    Long getParentId();

    /**
     * 子节点
     *
     * @return List<T>
     */
    List<T> getChildren();

    /**
     * 是否有子节点
     *
     * @return Boolean
     */
    default Boolean getHasChildren() {
        return Boolean.FALSE;
    }
}