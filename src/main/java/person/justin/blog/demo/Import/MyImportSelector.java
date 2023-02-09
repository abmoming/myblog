package person.justin.blog.demo.Import;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>
 *
 * @author gym on 2023-02-01 15:41
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // return new String[]{"person.justin.blog.demo.Import.Bean01", "person.justin.blog.demo.Import.Bean02"};
        return new String[0];
    }
}
