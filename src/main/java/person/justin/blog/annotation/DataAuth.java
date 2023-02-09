package person.justin.blog.annotation;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import person.justin.blog.constant.DataScopeConstant;
import person.justin.blog.enums.DataScopeEm;

import java.lang.annotation.*;

/**
 * <p>数据权限定义
 *
 * @author gym on 2023-02-06 10:25
 */
@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataAuth {

    /**
     * 资源编号
     */
    String code() default "";

    /**
     * 获取权限对应字段
     */
    String column() default DataScopeConstant.DEFAULT_COLUMN;

    /**
     * 数据权限类型
     */
    DataScopeEm scopeType() default DataScopeEm.ALL;

    /**
     * 可见字段
     */
    String visibleField() default "*";

    /**
     * 数据权限规则值(只有数据权限类型为自定义时用得上)
     */
    String value() default "";
}