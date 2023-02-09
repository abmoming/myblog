package person.justin.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>角色实体类
 *
 * @author gym on 2023-01-08 11:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_role")
public class Role extends BasePojo {

    private static final long serialVersionUID = -2019796104818247409L;
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 角色英文名称
     */
    private String name;
    /**
     * 角色中文名称(别名)
     */
    private String alias;
    /**
     * 排序
     */
    private Integer sort;
}