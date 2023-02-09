package person.justin.blog.node;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;
import person.justin.blog.constant.CommonConstant;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>森林节点管理器
 *
 * @author gym on 2023-01-19 21:12
 */
public class ForestNodeManager<T extends Node<T>> {

    /**
     * 森林的所有节点
     */
    private final Map<Long, T> nodeMap;

    /**
     * 森林的父节点
     */
    private final Map<Long, Object> parentIdMap = new HashMap<>();

    public ForestNodeManager(@NonNull List<T> items) {
        nodeMap = items.stream().collect(Collectors.toMap(Node::getId, Function.identity(), (k1, k2) -> k2));
    }

    /**
     * 通过主键ID获取森林的一个节点
     *
     * @param id 主键ID
     * @return Node
     */
    public Node<T> getTreeNode(Long id) {

        if (nodeMap.containsKey(id)) {
            return nodeMap.get(id);
        }
        return null;
    }

    /**
     * 添加森林的父节点容器数据
     *
     * @param id 父节点ID
     */
    public void addParentId(@NonNull Long id) {
        parentIdMap.put(id, StringPool.EMPTY);
    }

    /**
     * 获取森林的所有根节点
     *
     * @return List<T>
     */
    public List<T> getRoot() {

        List<T> roots = new ArrayList<>();
        if (CollectionUtil.isEmpty(nodeMap)) {
            return roots;
        }

        nodeMap.forEach((key, node) -> {
            if (CommonConstant.HIGHEST_PARENT_MENU == node.getParentId() || parentIdMap.containsKey(key)) {
                roots.add(node);
            }
        });
        return roots;
    }
}