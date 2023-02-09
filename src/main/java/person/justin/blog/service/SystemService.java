package person.justin.blog.service;

import person.justin.blog.pojo.Role;
import person.justin.blog.pojo.User;

import java.util.List;

/**
 * <p>系统业务层
 *
 * @author gym on 2023-01-18 19:37
 */
public interface SystemService {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名称
     * @return User
     */
    User getUser(String username);

    /**
     * 通过用户ID查询角色集
     *
     * @param userId 用户ID
     * @return List
     */
    List<Role> listRole(long userId);

    //List<>
}
