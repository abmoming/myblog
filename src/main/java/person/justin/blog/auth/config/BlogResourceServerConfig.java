package person.justin.blog.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import person.justin.blog.auth.filter.AuthFilter;

/**
 * <p>资源服务器
 *
 * @author gym on 2023-02-14 15:09
 */
@SuppressWarnings({"deprecation"})
@RequiredArgsConstructor
@SpringBootConfiguration
@EnableResourceServer
public class BlogResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AuthFilter authFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // http.headers().frameOptions().disable(); //防止iframe内容无法显示
        http    // 不全盘接管，先随机暂定一个地址（这是将授权服务和资源服务整在一起才需要这样配置，好像加上gateway就不用这么配）
                .requestMatchers()
                .antMatchers("/r/**")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .csrf()
                .disable();
                // .sessionManagement()
                // .maximumSessions(1);
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
    }
}