package person.justin.blog.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import person.justin.blog.pojo.Menu;
import person.justin.blog.pojo.vo.MenuVO;

import java.util.List;

/**
 * <p>菜单业务层
 *
 * @author gym on 2023-01-18 20:02
 */
public interface MenuService extends IService<Menu> {

    /**
     * 通过用户ID查询路由
     * @param userId 用户ID
     * @return List<MenuVO>
     */
    List<MenuVO> routers(Long userId);
}
