package person.justin.blog.demo.color;

import org.springframework.beans.factory.FactoryBean;

/**
 * <p>ColorFactoryBean
 *
 * @author gym on 2023-02-01 16:29
 */
public class ColorFactoryBean implements FactoryBean<Color> {

    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
