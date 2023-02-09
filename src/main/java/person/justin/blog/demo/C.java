package person.justin.blog.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author gym on 2023-01-29 13:38
 */
@Order(2)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class C implements A{

    private Long id;
    private String name;

    @Override
    public void demo() {
        System.out.println("c");
    }

    /*@Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }*/
}
