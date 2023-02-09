package person.justin.blog.datascope.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>数据权限参数配置
 *
 * @author gym on 2023-02-06 09:28
 */
@Data
@ConfigurationProperties(prefix = "blog.data-scope")
public class DataScopeProperties {

    /**
     * 开启数据权限
     */
    private Boolean enabled = Boolean.TRUE;

    /**
     * mapper方法匹配关键字
     */
    private List<String> mapperKey = Arrays.asList("Page", "page", "List", "list");

    /**
     * mapper过滤
     * FlowMapper：工作流的mapper
     */
    private List<String> mapperExclude = Collections.singletonList("FlowMapper");
}