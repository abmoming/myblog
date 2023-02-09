package person.justin.blog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Jackson工具类
 *
 * @author gym on 2023-02-07 19:24
 */
@Slf4j
public class JsonUtil {

    /**
     * 对象转为json根式
     *
     * @param val 对象
     * @return String
     */
    public static <T> String toJsonStr(T val) {
        try {
            return getInstance().writeValueAsString(val);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static ObjectMapper getInstance() {
        return JacksonHolder.INSTANCE;
    }

    private static class JacksonHolder {
        private static final ObjectMapper INSTANCE = new JacksonObjectMapper();
    }

    private static class JacksonObjectMapper extends ObjectMapper {

        private static final long serialVersionUID = 8050540746105928543L;

        public JacksonObjectMapper(ObjectMapper src) {
            super(src);
        }

        public JacksonObjectMapper() {
            super();
            // TODO ObjectMapper：一系列的参数设置
        }

        @Override
        public ObjectMapper copy() {
            return new JacksonObjectMapper(this);
        }
    }
}
