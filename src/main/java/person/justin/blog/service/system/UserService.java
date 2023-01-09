package person.justin.blog.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import person.justin.blog.pojo.Role;
import person.justin.blog.pojo.User;

import java.util.List;

/**
 * <p>用户业务层
 *
 * @author gym on 2023-01-08 21:27
 */
public interface UserService extends IService<User> {

    /**
     * 通过用户ID查询角色
     *
     * @param userId 用户ID
     * @return java.util.List
     */
    List<Role> listRole(Long userId);
}
