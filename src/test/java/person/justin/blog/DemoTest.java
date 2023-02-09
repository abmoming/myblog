package person.justin.blog;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

import java.util.ArrayList;

/**
 * <p>
 *
 * @author gym on 2023-02-07 14:10
 */
public class DemoTest {

    public static void main(String[] args) {
        /*String str = "person.justin.blog.mapper.UserMapper.selectOne";
        String methodName = str.substring(str.lastIndexOf(StringPool.DOT) + 1);
        System.out.println("methodName ---> " + methodName);*/
        String str = "bc";
        ArrayList<String> list = new ArrayList<>();
        list.add("abcd");
        boolean b = list.stream().noneMatch(str::contains);
        System.out.println(b);

        System.out.println("str".contains("s"));

        System.out.println(list.contains("abcdefg"));
        System.out.println(list.stream().anyMatch("abcdefg"::contains));
    }
}
