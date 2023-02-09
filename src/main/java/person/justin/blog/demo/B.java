package person.justin.blog.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author gym on 2023-01-29 13:14
 */
// @Primary
@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class B implements A, Ordered{

    private Long id;
    private String name;

    @Override
    public void demo() {
        System.out.println("b");
    }

    @Override
    public int getOrder() {
        return 1;
    }

/*    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }*/
}