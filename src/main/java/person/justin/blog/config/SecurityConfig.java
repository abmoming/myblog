package person.justin.blog.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import person.justin.blog.pojo.LoginUser;

/**
 * <p>Spring Security配置
 * 新版WebSecurityConfigurerAdapter标记为过时,
 * 改为以下方案：
 * SecurityFilterChain来配置HttpSecurity,
 * WebSecurityCustomizer来配置WebSecurity.
 *
 * @author gym on 2023-01-07 19:29
 */
@SpringBootConfiguration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").passwordEncoder(password -> passwordEncoder().encode(password)).password("123").roles("admin").build());
        manager.createUser(User.withUsername("user").passwordEncoder(password -> passwordEncoder().encode(password)).password("123").roles("user").build());

        return manager;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/hello-admin/**").hasRole("admin")
                .antMatchers("/hello-user/**").hasAnyRole("admin", "user")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .csrf()
                .disable();
        return httpSecurity.build();
    }

    /*@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {

        };
    }*/
}
