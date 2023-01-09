package person.justin.blog.service.system.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import person.justin.blog.enums.AccountStateEm;
import person.justin.blog.pojo.LoginUser;
import person.justin.blog.pojo.User;
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
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.lambdaQuery().eq(User::getUsername, username).eq(User::getHasDelete, 0).one();
        if (ObjectUtil.isEmpty(user)) {
            throw new UsernameNotFoundException(StrUtil.format("用户:{},不存在", username));
        }

        return createLoginUser(user);
    }

    /**
     * 校验用户(好像不用在这做校验，springsecurity自动会做校验)  待验证
     *
     * @param user
     * @return
     */
    /*private boolean validAccountState(User user) {
        int state = user.getState();
        if(state == AccountStateEm.ACCOUNT_EXPIRED.getCode()){

        }
    }*/
    private LoginUser createLoginUser(User user) {

        return LoginUser.builder()
                .user(user)
                .roles(userService.listRole(user.getId())).build();
    }
}
