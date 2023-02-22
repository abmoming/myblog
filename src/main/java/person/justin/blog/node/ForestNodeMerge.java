package person.justin.blog.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;
import person.justin.blog.constant.CommonConstant;

import java.awt.event.ItemEvent;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>森林节点合并类
 *
 * @author gym on 2023-01-19 22:23
 */
public class ForestNodeMerge {

    /**
     * 将节点数组归并成一个森林（多棵树）（填充节点的children域）
     * 时间复杂度为O(n^2)
     *
     * @param items 节点域
     * @return List<T> 多棵树的根节点
     */
    public static <T extends Node<T>> List<T> merge(@NonNull List<T> items) {

        // 初始化森林所有节点的容器
        ForestNodeManager<T> forestNodeManager = new ForestNodeManager<>(items);
        items.forEach(forestNode -> {
            if (CommonConstant.HIGHEST_PARENT_MENU != forestNode.getParentId()) {
                // 通过子节点的parentId查找父节点，找到则将当前子字节点填充到父节点的children属性；否则添加到森林管理器的森林父节点容器中；
                Node<T> parentNode = forestNodeManager.getTreeNode(forestNode.getParentId());
                if (Objects.nonNull(parentNode)) {
                    parentNode.getChildren().add(forestNode);
                } else {
                    forestNodeManager.addParentId(forestNode.getId());
                }
            }
        });
        return forestNodeManager.getRoot();
    }
}