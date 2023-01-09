package person.justin.blog.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import person.justin.blog.mapper.UserMapper;
import person.justin.blog.pojo.Role;
import person.justin.blog.pojo.User;
import person.justin.blog.service.system.UserService;

import java.util.List;

/**
 * <p>用户业务层实现
 *
 * @author gym on 2023-01-08 21:38
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<Role> listRole(Long userId) {
        return baseMapper.listRole(userId);
    }
}
