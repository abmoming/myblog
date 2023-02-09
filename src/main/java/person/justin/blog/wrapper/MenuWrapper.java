package person.justin.blog.wrapper;

import cn.hutool.core.bean.BeanUtil;
import person.justin.blog.mybatis.support.BaseEntityWrapper;
import person.justin.blog.node.ForestNodeMerge;
import person.justin.blog.pojo.Menu;
import person.justin.blog.pojo.vo.MenuVO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>菜单包装类
 *
 * @author gym on 2023-01-28 15:25
 */
public class MenuWrapper extends BaseEntityWrapper<Menu, MenuVO> {

    @Override
    public MenuVO entityVO(Menu entity) {
        return Objects.requireNonNull(BeanUtil.toBean(entity, MenuVO.class));
    }

    public List<MenuVO> listNodeVo(List<Menu> menus) {

        List<MenuVO> voList = menus.stream().map(menu -> BeanUtil.toBean(menu, MenuVO.class)).collect(Collectors.toList());
        return ForestNodeMerge.merge(voList);
    }
}