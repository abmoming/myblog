package person.justin.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.justin.blog.annotation.WebResponseExclude;
import person.justin.blog.demo.DemoService;
import person.justin.blog.node.BaseNode;
import person.justin.blog.pojo.Menu;
import person.justin.blog.pojo.Role;
import person.justin.blog.pojo.vo.MenuVO;
import person.justin.blog.service.SystemService;
import person.justin.blog.service.system.RoleService;
import person.justin.blog.utils.JsonUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author gym on 2023-01-07 22:33
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private RoleService roleService;

    @WebResponseExclude
    @GetMapping("/hello-admin")
    public Object admin(){
        return "admin";
    }

    @WebResponseExclude
    @RequestMapping("/hello-user")
    public String user() {
        return "user";
    }

    @WebResponseExclude
    @RequestMapping("/hello")
    public String hello() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        return "hello";
    }

    @RequestMapping("/demo")
    public BaseNode demo(@NonNull String str){
        Menu menu = new Menu();
        menu.setId(123456789L);
        System.out.println(menu);
        System.out.println(JsonUtil.toJsonStr(menu));
        BaseNode baseNode = new BaseNode();
        baseNode.setId(6666666666L);
        //System.out.println(new ObjectMapper().writeValueAsString(baseNode));
        return baseNode;
    }

    @RequestMapping("/demo2")
    public MenuVO demo2(@NonNull String str){
        MenuVO menuVO = new MenuVO();
        menuVO.setId(666L);
        menuVO.setParentId(0L);
        menuVO.setCreateTime(LocalDateTime.now());
        //menuVO.set

        return menuVO;
    }

    @RequestMapping("/demo03")
    public void demo03() {
        demoService.demoObjectProvider();
    }

    @RequestMapping("/demo04")
    public List<Role> demo04() {
        return roleService.list();
    }

    @RequestMapping("/auth")
    public Map<String, Object> auth(String code) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        System.out.println("code ---> " + code);
        return map;
    }
}