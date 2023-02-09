package person.justin.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import person.justin.blog.constant.CommonConstant;
import person.justin.blog.model.LoginUser;
import person.justin.blog.pojo.User;
import person.justin.blog.service.SystemService;
import person.justin.blog.service.system.RoleService;
import person.justin.blog.service.system.UserService;

/**
 * <p>校验用户是否存在
 *
 * @author gym on 2023-01-08 21:26
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SystemService systemService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = systemService.getUser(username);
        if (ObjectUtil.isEmpty(user)) {
            throw new UsernameNotFoundException(StrUtil.format("用户:{},不存在", username));
        }

        return createLoginUser(user);
    }

    private LoginUser createLoginUser(User user) {
        return new LoginUser(user, systemService.listRole(user.getId()));
    }
}
