package person.justin.blog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>实体基类
 *
 * @author gym on 2023-01-07 17:56
 */
@Data
public abstract class BasePojo implements Serializable {
    /**
     * 是否删除
     */
    private Integer hasDelete;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 更新人
     */
    private String updater;
}
