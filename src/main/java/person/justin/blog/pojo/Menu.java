package person.justin.blog.pojo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>菜单
 *
 * @author gym on 2023-01-18 19:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_menu")
public class Menu extends BasePojo {

    private static final long serialVersionUID = -458259017919585238L;
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 父级菜单ID
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 是否外链
     */
    private Integer hasFrame;
    /**
     * 菜单类别(M目录 C菜单 F按钮)
     */
    private String category;
    /**
     * 是否显示(0-否;1-是)
     */
    private Integer hasVisible;
    /**
     * 菜单状态(0-禁用,1-启用)
     */
    private Integer state;
    /**
     * 权限标识
     */
    private String permissionSign;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单排序
     */
    private Integer sort;
}