package person.justin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import person.justin.blog.pojo.Menu;
import person.justin.blog.pojo.vo.MenuVO;

import java.util.List;

/**
 * <p>菜单数据层
 *
 * @author gym on 2023-01-18 20:04
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询所有菜单(类型为M和C)
     *
     * @return List<Menu>
     */
    List<Menu> listMenu();

    /**
     * 通过用户ID查询路由
     *
     * @param userId 用户ID
     * @return List<Menu>
     */
    List<Menu> listRoleMenu(Long userId);
}
