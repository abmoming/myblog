package person.justin.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import person.justin.blog.constant.CommonConstant;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>实体基类
 *
 * @author gym on 2023-01-07 17:56
 */
@Data
public abstract class BasePojo implements Serializable {

    private static final long serialVersionUID = 1L;
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
    private Long creator;

    /**
     * 创建部门
     */
    private Long createDept;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 更新人
     */
    private Long updater;
}