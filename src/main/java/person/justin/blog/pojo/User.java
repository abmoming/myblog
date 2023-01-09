package person.justin.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>用户实体类
 *
 * @author gym on 2023-01-07 17:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_user")
public class User extends BasePojo {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 登录账号(用户名)
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户状态
     */
    private Integer state;
    /**
     * 账号状态
     */
    private Integer accountState;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 邮件
     */
    private String email;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 来源方
     */
    private String source;
    /**
     * 个人简介
     */
    private String signature;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
}
