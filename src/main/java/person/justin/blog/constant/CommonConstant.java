package person.justin.blog.constant;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

/**
 * <p>公用常量类
 *
 * @author gym on 2023-01-10 00:41
 */
public class CommonConstant implements StringPool {

    /**
     * 未删除
     */
    public static final int NOT_DELETE = 0;
    /**
     * 已删除
     */
    public static final int DELETE = 1;
    /**
     * 字符参数status
     */
    public static final String STATUS = "status";
    /**
     * 字符参数data
     */
    public static final String DATA = "data";
    /**
     * 字符参数msg
     */
    public static final String MESSAGE = "msg";
    /**
     * 字符参数success
     */
    public static final String SUCCESS = "success";

    /**
     * 最高级的父级菜单
     */
    public static final long HIGHEST_PARENT_MENU = 0L;

    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 响应内容编码格式
     */
    public static final String APPLICATION_JSON_UTF8= "application/json;charset=utf-8";
}
