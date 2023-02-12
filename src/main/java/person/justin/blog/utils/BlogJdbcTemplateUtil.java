package person.justin.blog.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>jdbcTemplate 工具类
 *
 * @author gym on 2023-02-10 11:09
 */
// @Component
// @AllArgsConstructor
@Slf4j
public class BlogJdbcTemplateUtil {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbcTemplate() {
        if (Objects.isNull(jdbcTemplate)) {
            // 这里能去springUtil拿吗？
            jdbcTemplate = SpringUtil.getBean(JdbcTemplate.class);
        }
        return jdbcTemplate;
    }

    /**
     * 查询内容
     *
     * @param sql   sql
     * @param clazz 转换的Bean
     * @param <T>   泛型定义
     * @return List<T>
     */
    public static <T> List<T> select(String sql, Class<T> clazz) {

        try {
            return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(clazz));
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 查询内容
     *
     * @param sql   sql
     * @param clazz 转换的Bean
     * @param args  条件参数
     * @param <T>   泛型定义
     * @return List<T>
     */
    public static <T> List<T> select(String sql, Class<T> clazz, Object... args) {

        try {
            return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(clazz), args);
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }

        return Collections.emptyList();
    }
}
