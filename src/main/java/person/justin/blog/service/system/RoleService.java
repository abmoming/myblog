package person.justin.blog.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import person.justin.blog.pojo.Role;

import java.util.List;

/**
 * <p>角色业务层
 *
 * @author gym on 2023-01-08 21:28
 */
public interface RoleService extends IService<Role> {

    /**
     * 通过用户ID查询角色
     *
     * @param userId 用户ID
     * @return List<Role>
     */
    List<Role> listRole(long userId);
}
