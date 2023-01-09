package person.justin.blog.enums;

import lombok.Getter;

/**
 * <p>账号状态枚举类
 *
 * @author gym on 2023-01-08 18:50
 */
@Getter
public enum AccountStateEm {

    /**
     * 未知
     */
    UN_KNOWN(-1, "未知"),

    /**
     * 禁用
     */
    UN_ENABLE(0,"禁用"),
    /**
     * 账户已过期
     */
    ACCOUNT_EXPIRED(1, "账户已过期"),
    /**
     * 账户已被锁
     */
    ACCOUNT_LOCKED(3, "账户已被锁"),
    /**
     * 认证信息已过期
     */
    CREDENTIALS_EXPIRED(4, "认证信息已过期"),
    /**
     * 已启用
     */
    ENABLED(5, "已启用"),
    ;

    /**
     * 编号
     */
    private final int code;
    /**
     * 名称
     */
    private final String name;

    AccountStateEm(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static AccountStateEm getByCode(int code) {
        for (AccountStateEm val : AccountStateEm.values()) {
            if (code == val.getCode()) {
                return val;
            }
        }
        return UN_KNOWN;
    }

    public static boolean isEnabled(int code) {
        return code != ACCOUNT_EXPIRED.getCode()
                && code != ACCOUNT_LOCKED.getCode()
                && code != CREDENTIALS_EXPIRED.getCode();
    }
}
