package person.justin.blog.node;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import person.justin.blog.pojo.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>节点基类
 *
 * @author gym on 2023-01-19 21:04
 */
@Data
public class BaseNode<T> implements Node<T> {

    /**
     * 主键
     */
    //@JsonSerialize(using = ToStringSerializer.class)
    protected Long id;

    /**
     * 父级主键
     */
    //@JsonSerialize(using = ToStringSerializer.class)
    protected Long parentId;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<T> children = new ArrayList<>();

    /**
     * 是否有子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Boolean hasChildren;

    @Override
    public Boolean getHasChildren() {
        return CollectionUtil.isNotEmpty(children);
    }
}