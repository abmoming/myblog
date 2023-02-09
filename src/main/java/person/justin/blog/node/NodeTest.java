package person.justin.blog.node;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>NodeTest
 *
 * @author gym on 2023-01-20 10:59
 */
public class NodeTest {

    public static void main(String[] args) {
        List<ForestNode> items = new ArrayList<>();
        items.add(new ForestNode(1L,0L,"父节点1L"));
        items.add(new ForestNode(2L,1L,"子节点2L"));
        items.add(new ForestNode(3L,1L,"子节点3L"));
        items.add(new ForestNode(4L,1L,"子节点4L"));
        items.add(new ForestNode(5L,1L,"子节点5L"));
        items.add(new ForestNode(6L,1L,"子节点6L"));
        List<ForestNode> list = ForestNodeMerge.merge(items);
        list.forEach(node -> System.out.println(JSONUtil.toJsonStr(node)));
    }
}
