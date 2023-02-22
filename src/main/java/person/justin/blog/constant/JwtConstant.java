package person.justin.blog.constant;

/**
 * <p>Jwt常量类
 *
 * @author gym on 2023-02-16 11:47
 */
public class JwtConstant {

    /**
     * 默认生成jwt秘钥
     */
    public static final String DEFAULT_SECRET_KEY = "itisabloggingsystemthathelpsyousortthroughthepiecesofknowledgeinyourhead";

    /**
     * 秘钥安全长度(大于等于32)
     */
    public static final int SECRET_KEY_LENGTH = 32;
}