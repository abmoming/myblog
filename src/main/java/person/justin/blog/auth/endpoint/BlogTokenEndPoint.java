package person.justin.blog.auth.endpoint;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import person.justin.blog.redis.BlogRedis;
import person.justin.blog.redis.CacheName;
import person.justin.blog.utils.Kv;

import java.time.Duration;
import java.util.Map;

/**
 * <p>BlogTokenEndPoint
 *
 * @author gym on 2023-03-03 19:18
 */
@RestController
@AllArgsConstructor
public class BlogTokenEndPoint {

    private final BlogRedis blogRedis;

    @GetMapping("/oauth/captcha")
    public Map<String, Object> captcha() {

        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(130, 45, 4, 30);
        String imageBase64 = lineCaptcha.getImageBase64Data();
        String captchaKey = UUID.fastUUID().toString(true);
        String captchaCode = lineCaptcha.getCode().toLowerCase();
        blogRedis.setEx(CacheName.CAPTCHA_CACHE.concat(captchaKey), captchaCode, Duration.ofMinutes(20));
        return Kv.create().set("captchaKey", captchaKey).set("imageBase64", imageBase64);
    }
}