package person.justin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import person.justin.blog.pojo.Role;

import java.util.List;

/**
 * <p>角色用户数据层
 *
 * @author gym on 2023-01-08 22:17
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户ID查询角色集
     *
     * @param userId 用户ID
     * @return List<Role>
     */
    List<Role> listRole(Long userId);
}
