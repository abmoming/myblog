package person.justin.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.justin.blog.constant.CommonConstant;
import person.justin.blog.pojo.Role;
import person.justin.blog.pojo.User;
import person.justin.blog.service.SystemService;
import person.justin.blog.service.system.RoleService;
import person.justin.blog.service.system.UserService;

import java.util.List;

/**
 * <p>系统业务层实现
 *
 * @author gym on 2023-01-18 19:39
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public User getUser(String username) {

        return userService.lambdaQuery()
                .eq(User::getUsername, username)
                .eq(User::getHasDelete, CommonConstant.NOT_DELETE)
                .one();
    }

    @Override
    public List<Role> listRole(long userId) {
        return roleService.listRole(userId);
    }
}