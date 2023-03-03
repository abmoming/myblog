package person.justin.blog.redis;

import lombok.Getter;
import org.springframework.data.redis.core.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * <p>redis缓存工具
 *
 * @author gym on 2023-02-22 19:05
 */
@SuppressWarnings({"unchecked"})
@Getter
public class BlogRedis {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ValueOperations<String, Object> valueOps;

    private final HashOperations<String, Object, Object> hashOps;

    private final ListOperations<String, Object> listOps;

    private final SetOperations<String, Object> setOps;

    private final ZSetOperations<String, Object> zSetOps;

    public BlogRedis(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOps = redisTemplate.opsForValue();
        this.hashOps = redisTemplate.opsForHash();
        this.listOps = redisTemplate.opsForList();
        this.setOps = redisTemplate.opsForSet();
        this.zSetOps = redisTemplate.opsForZSet();
    }

    /**
     * 设置string类型参数
     *
     * @param key     key
     * @param value   value
     * @param seconds 时间戳(秒)
     */
    public void setEx(String key, Object value, long seconds) {
        valueOps.set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void setEx(String key, Object value, Duration timeout) {
        valueOps.set(key, value, timeout);
    }

    /**
     * 存放key，并将key生存时间设置为timeout
     * 如果key存在，不会覆写旧值，并返回false
     *
     * @param key     key
     * @param value   value
     * @param timeout timeout
     * @return boolean
     */
    public Boolean setExNx(String key, Object value, Duration timeout) {
        return valueOps.setIfAbsent(key, value, timeout);
    }

    /**
     * 存放key，并将key生存时间设置为seconds(秒)
     * 如果key存在，不会覆写旧值，并返回false
     *
     * @param key     key
     * @param value   value
     * @param seconds seconds
     * @return boolean
     */
    public Boolean setExNx(String key, Object value, long seconds) {
        return valueOps.setIfAbsent(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * 通过key获取缓存内容
     *
     * @param key key
     * @return <T> T
     */
    public <T> T get(String key) {
        return (T) valueOps.get(key);
    }

    /**
     * 获取key缓存，并删除key缓存
     *
     * @param key key
     * @return <T> T
     */
    public <T> T getAndDel(String key) {
        return (T) valueOps.getAndDelete(key);
    }

    /**
     * 通过key删除缓存
     *
     * @param key key
     * @return Boolean
     */
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }
}