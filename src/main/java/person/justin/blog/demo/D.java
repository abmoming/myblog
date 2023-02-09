package person.justin.blog.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author gym on 2023-01-29 21:43
 */
@Component(value = "d")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class D {

    private Long id;
    private String name;
}
