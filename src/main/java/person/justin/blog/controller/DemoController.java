package person.justin.blog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.justin.blog.pojo.LoginUser;

/**
 * <p>
 *
 * @author gym on 2023-01-07 22:33
 */
@RestController
public class DemoController {

    @GetMapping("/hello-admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/hello-user")
    public String user() {
        return "user";
    }

    @RequestMapping("/hello")
    public String hello() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        return "hello";
    }
}
