package person.justin.blog.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import person.justin.blog.mapper.MenuMapper;
import person.justin.blog.pojo.Menu;
import person.justin.blog.pojo.vo.MenuVO;
import person.justin.blog.service.system.MenuService;
import person.justin.blog.wrapper.MenuWrapper;

import java.util.*;

/**
 * <p>菜单业务实现层
 *
 * @author gym on 2023-01-18 20:03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<MenuVO> routers(Long userId) {

        if (ObjectUtil.isEmpty(userId)) {
            return Collections.emptyList();
        }

        // 查出用户的角色对应的菜单
        // 查出所有的目录以及菜单级别的菜单
        // 用所有的菜单和用户的菜单进行匹配
        // 这里要改成查M和C类型的

        // 这里可以添加个角色报
        List<Menu> allMenus = baseMapper.listMenu();
        List<Menu> roleMenus = baseMapper.listRoleMenu(userId);

        if (CollectionUtil.isEmpty(allMenus) || CollectionUtil.isEmpty(roleMenus)) {
            return Collections.emptyList();
        }

        return buildRouters(allMenus, roleMenus);
    }

    /**
     * 构建路由
     *
     * @param allMenus  所有菜单
     * @param roleMenus 角色的所有菜单
     * @return List<MenuVO>
     */
    private List<MenuVO> buildRouters(List<Menu> allMenus, List<Menu> roleMenus) {
        // 浅拷贝，不影响源集合的大小
        List<Menu> routers = new LinkedList<>(roleMenus);
        roleMenus.forEach(roleMenu -> fillParentMenu(allMenus, routers, roleMenu));
        routers.sort(Comparator.comparing(Menu::getSort));
        // 转成森林节点
        return new MenuWrapper().listNodeVo(routers);
    }

    /**
     * 填充父级菜单
     *
     * @param allMenus 所有菜单
     * @param routers  角色的所有菜单
     * @param roleMenu 角色当前遍历的菜单
     */
    private void fillParentMenu(List<Menu> allMenus, List<Menu> routers, Menu roleMenu) {
        // 返回满足条件的第一个元素
        Optional<Menu> parentMenu = allMenus.stream().filter(m -> Objects.equals(m.getId(), roleMenu.getParentId())).findFirst();
        if (parentMenu.isPresent() && !routers.contains(parentMenu.get())) {
            routers.add(parentMenu.get());
            fillParentMenu(allMenus, routers, parentMenu.get());
        }
    }
}