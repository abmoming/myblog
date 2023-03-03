package person.justin.blog.utils;

import org.springframework.util.LinkedCaseInsensitiveMap;

/**
 * <p>忽略key大小写的链式map
 *
 * @author gym on 2023-03-03 20:03
 */
public class Kv extends LinkedCaseInsensitiveMap<Object> {

    private static final long serialVersionUID = 1L;

    public static Kv create() {
        return new Kv();
    }

    public Kv set(String key, Object value) {
        super.put(key, value);
        return this;
    }
}