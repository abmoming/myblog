package person.justin.blog.constant;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

/**
 * <p>授权常量类
 *
 * @author gym on 2023-02-16 21:26
 */
public class AuthConstant {

    /**
     * blog_client表字段
     */
    public static final String CLIENT_COLUMN = "client_id, client_secret, resource_ids, scope, " +
            "authorized_grant_types, web_server_redirect_uri, " +
            "authorities, access_token_validity, refresh_token_validity, " +
            "additional_information, autoapprove";

    /**
     * blog_client基本查询语句
     */
    public static final String BASE_STATEMENT = "select".concat(StringPool.SPACE)
            .concat(CLIENT_COLUMN)
            .concat(StringPool.SPACE)
            .concat("from blog_client")
            .concat(StringPool.SPACE);

    /**
     * 默认查询语句
     */
    public static final String DEFAULT_SELECT_STATEMENT = BASE_STATEMENT.concat("where client_id = ?");

    /**
     * 默认排序语句
     */
    public static final String DEFAULT_FIND_STATEMENT = BASE_STATEMENT.concat("order by client_id");
}