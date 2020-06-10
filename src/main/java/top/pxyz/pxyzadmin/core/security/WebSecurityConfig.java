package top.pxyz.pxyzadmin.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Ijiran
 * @Package top.pxyz.pxyzadmin.core.security
 * @date 2020-06-10 23:22
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Resource
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //将自定义的CustomAuthenticationProvider装配到AuthenticationManagerBuilder
        auth.authenticationProvider(customAuthenticationProvider);
        //将自定的CustomUserDetailsService装配到AuthenticationManagerBuilder
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();//开启跨域
        http.headers().frameOptions().disable();//关闭防止iframe
        http
                .authorizeRequests()
                .antMatchers("/login/login").permitAll()
                .anyRequest().authenticated()//其他的路径都是登录后才可访问
                .and()
                /*登录配置*/
                .formLogin()
                .loginPage("/login/login")// 设置登陆页
                .defaultSuccessUrl("/index").permitAll()// 设置登陆成功页
                .failureUrl("/login/login?error")
                .permitAll()
                .and()
                /*登出配置*/
                .logout().logoutSuccessUrl("/login/login?logout").permitAll();
    }

    /**
     * security检验忽略的请求，比如静态资源不需要登录的可在本处配置
     * @param web
     */
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    //密码加密配置
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
