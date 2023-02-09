package person.justin.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

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

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    @Qualifier("blogAuthenticationFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {

        return auth.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/hello-admin/**").hasRole("admin")
                .antMatchers("/hello-user/**").hasAnyRole("admin", "user")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .userDetailsService(userDetailsService);
        return http.build();
    }

    /*@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {

        };
    }*/

    //public static void main(String[] args) {
    //    System.out.println(new SecurityConfig().passwordEncoder().encode("123"));
    //}
}
