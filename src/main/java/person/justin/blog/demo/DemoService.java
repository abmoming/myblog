package person.justin.blog.demo;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import person.justin.blog.demo.A;
import person.justin.blog.demo.B;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 *
 * @author gym on 2023-01-29 13:32
 */
@Service
public class DemoService {

    @Autowired
    private ObjectProvider<A[]> objectProvider;
    // @Autowired
    // private A a;

    // @Bean
    // @ConditionalOnProperty(prefix = "blog.b")
    // @ConditionalOnMissingBean
    // public B b(){
    //     return new B(1L, "B名称");
    // }

    public void demoObjectProvider() {
        // a.demo();
        // objectProvider.
        System.out.println("初始化objectProvider:getIfAvailable--->"+ Arrays.toString(objectProvider.getIfAvailable()));
        // System.out.println("初始化objectProvider判断是否为空--->"+ ObjectUtil.isEmpty(objectProvider.getIfAvailable()));
        // System.out.println("初始化objectProvider:getObject--->"+ objectProvider.getObject());
        // System.out.println("初始化objectProvider:getIfUnique--->"+ objectProvider.getIfUnique());
        // System.out.println("初始化objectProvider:getIfAvailable--->"+objectProvider.getIfAvailable());
        // System.out.println("初始化objectProvider:getIfAvailable--->"+objectProvider.getIfAvailable(B::new));


    }

    public static void main(String[] args) {
        ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.putIfAbsent("a", 1L);
        concurrentMap.putIfAbsent("a", 2L);
        concurrentMap.put("a", 3L);
        System.out.println(concurrentMap);
        Class<?>[] allInterfaces = ClassUtils.getAllInterfaces(B.class);
        for (Class<?> allInterface : allInterfaces) {
            for (Method method : allInterface.getDeclaredMethods()) {
                System.out.println(method.getName());
            }
        }
        // System.out.println(allInterfaces);
    }
}