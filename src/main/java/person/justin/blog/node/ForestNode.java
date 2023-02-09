package person.justin.blog.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>森林节点
 *
 * @author gym on 2023-01-20 10:44
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ForestNode extends BaseNode<ForestNode> {

    /**
     * 内容
     */
    private Object content;

    public ForestNode(Long id, Long parentId, Object content) {
        this.id = id;
        this.parentId = parentId;
        this.content = content;
    }
}