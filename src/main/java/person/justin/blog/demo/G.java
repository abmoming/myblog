package person.justin.blog.demo;

import lombok.RequiredArgsConstructor;

/**
 * <p>
 *
 * @author gym on 2023-02-04 11:00
 */
@RequiredArgsConstructor
public class G {

    private final Integer i = 0;
    private final E e = new E(null);

    public static void main(String[] args) {
        new G();
    }
}
