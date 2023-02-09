package person.justin.blog.model;

import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * <p>统一返回结果类
 *
 * @author gym on 2023-01-12 21:47
 */
@Data
public class RespBean<T> implements Serializable {

    /**
     * 响应状态码
     */
    private int code;
    /**
     * 承载数据
     */
    private T data;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 是否成功
     */
    private boolean success;

    public RespBean(int code) {
        this(code, null, null);
    }

    public RespBean(int code, String msg) {
        this(code, null, msg);
    }

    public RespBean(int code, T data) {
        this(code, data, null);
    }

    public RespBean(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = HttpServletResponse.SC_OK == code;
    }

    public RespBean<T> setCode() {
        return this;
    }

    public RespBean<T> setData() {
        return this;
    }

    public RespBean<T> setMsg() {
        return this;
    }

    public static <T> RespBean<T> success() {
        return new RespBean<>(HttpServletResponse.SC_OK);
    }

    public static <T> RespBean<T> success(String msg) {
        return new RespBean<>(HttpServletResponse.SC_OK, msg);
    }

    public static <T> RespBean<T> success(T data) {
        return new RespBean<>(HttpServletResponse.SC_OK, data);
    }

    public static <T> RespBean<T> success(T data, String msg) {
        return new RespBean<>(HttpServletResponse.SC_OK, data, msg);
    }

    public static <T> RespBean<T> fail() {
        return new RespBean<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public static <T> RespBean<T> fail(String msg) {
        return new RespBean<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static <T> RespBean<T> fail(int code, String msg) {
        return new RespBean<>(code, msg);
    }
}
