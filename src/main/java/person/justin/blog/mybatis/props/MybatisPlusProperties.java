package person.justin.blog.mybatis.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>mybatis-plus属性配置
 *
 * @author gym on 2023-02-02 20:09
 */
@Data
@ConfigurationProperties(prefix = "blog.mybatis-plus")
public class MybatisPlusProperties {

    /**
     * 开启sql日志打
     */
    private Boolean hasSqlLog = true;

    /**
     * sql日志忽略打印关键字
     */
    private List<String> sqlLogExclude = new ArrayList<>();

    /**
     * 分页最大数
     */
    private Long maxLimit = 500L;

    /**
     * 溢出总页数后是否进行处理
     */
    private Boolean overflow = true;

    /**
     * join语句优化
     */
    private Boolean optimizeJoin = false;
}