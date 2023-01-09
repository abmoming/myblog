package person.justin.blog.enums;

import lombok.Data;
import lombok.Getter;

/**
 * <p>用户状态枚举类
 *
 * @author gym on 2023-01-08 11:59
 */
@Getter
public enum UserStateEm {

    /**
     * 未知
     */
    UN_KNOWN(-1, "未知"),
    /**
     * 离线
     */
    OFF_LINE(0, "离线"),
    /**
     * 在线
     */
    ON_LINE(1, "在线"),
    ;

    /**
     * 编号
     */
    private final int code;
    /**
     * 名称
     */
    private final String name;

    UserStateEm(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserStateEm getByCode(int code) {
        for (UserStateEm val : UserStateEm.values()) {
            if (code == val.getCode()) {
                return val;
            }
        }
        return UN_KNOWN;
    }
}
