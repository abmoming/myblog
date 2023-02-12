package person.justin.blog.pojo.vo;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import person.justin.blog.node.Node;
import person.justin.blog.pojo.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>菜单VO
 *
 * @author gym on 2023-01-19 20:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends Menu implements Node<MenuVO> {

    private static final long serialVersionUID = 1L;
    /**
     * 重写父类的getId()
     */
    private Long id;

    /**
     * 重写父类的getParentId()
     */
    private Long parentId;

    /**
     * 重写父类的getChildren()
     */
    private List<MenuVO> children;

    @Override
    public List<MenuVO> getChildren() {
        if (CollectionUtil.isEmpty(children)) {
            children = new ArrayList<>();
        }
        return children;
    }

    @Override
    public Boolean getHasChildren() {
        return CollectionUtil.isNotEmpty(children);
    }
}