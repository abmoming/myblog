package person.justin.blog.mybatis.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>视图包装基类
 *
 * @author gym on 2023-01-28 14:52
 */
public abstract class BaseEntityWrapper<E, V> {

    /**
     * 单个实体包装
     *
     * @param entity 实体类
     * @return V
     */
    public abstract V entityVO(E entity);

    /**
     * 实体类集合包装
     *
     * @param list 实体类集合
     * @return List<V>
     */
    public List<V> listVO(List<E> list) {
        return list.stream().map(this::entityVO).collect(Collectors.toList());
    }

    /**
     * 分页实体类集合包装
     *
     * @param page 分页对象
     * @return IPage<V>
     */
    public IPage<V> pageVO(IPage<E> page) {
        IPage<V> pageVO = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        pageVO.setRecords(listVO(page.getRecords()));
        return pageVO;
    }
}