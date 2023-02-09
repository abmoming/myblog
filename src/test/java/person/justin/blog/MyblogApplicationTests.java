package person.justin.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import person.justin.blog.demo.*;
import person.justin.blog.demo.Import.Bean01;
import person.justin.blog.demo.Import.Bean02;
import person.justin.blog.demo.color.Color;
import person.justin.blog.demo.color.ColorFactoryBean;
import person.justin.blog.mapper.RoleMapper;
import person.justin.blog.pojo.Role;
import person.justin.blog.pojo.User;
import person.justin.blog.pojo.vo.MenuVO;
import person.justin.blog.service.system.MenuService;
import person.justin.blog.service.system.RoleService;
import person.justin.blog.service.system.UserService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = MyBlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyblogApplicationTests {

    @Autowired
    private MenuService menuService;
    @Autowired
    private TT tt;
    @Autowired
    private TestConfig testConfig;
    @Autowired
    private Bean01 bean01;
    // @Autowired
    private Bean02 bean02;
    @Autowired
    private ColorFactoryBean colorFactoryBean;
    @Autowired
    private E e;

    @Autowired
    private ObjectProvider<Map<String, A>> aObjectProvider;


    @Test
    public void testRouters() {
        List<MenuVO> routers = menuService.routers(1L);
        System.out.println(routers);
    }

    @Test
    public void testImportAnnotation() {
        // System.out.println(tt.tt());
        System.out.println(bean01 == null);
        System.out.println(bean02 == null);
    }

    @Test
    public void testConfigurationProperties() {
        System.out.println(testConfig.getId() + "--->" + testConfig.getName());
    }

    @Test
    public void test01() throws Exception {
        Color color = colorFactoryBean.getObject();
        System.out.println(color == null);
    }

    @Test
    public void test02() {
        System.out.println("e是否为空：" + (e == null));
        e.demo();
    }

    @Test
    public void test03() {
        System.out.println(aObjectProvider.getIfAvailable());
        // System.out.println(aObjectProvider.getIfAvailable());
        // System.out.println(aObjectProvider.orderedStream().findFirst().get());
    }
}