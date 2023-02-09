package person.justin.blog.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import person.justin.blog.mapper.RoleMapper;
import person.justin.blog.pojo.Role;
import person.justin.blog.service.system.RoleService;

import java.util.List;

/**
 * <p>角色业务实现层
 *
 * @author gym on 2023-01-08 22:21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> listRole(long userId) {
        return baseMapper.listRole(userId);
    }
}
