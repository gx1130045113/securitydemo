package com.example.securitydemo.config;

import com.example.securitydemo.handler.MyAuthenticationFailureHandler;
import com.example.securitydemo.handler.MyExpiredSessionStrategy;
import com.example.securitydemo.handler.MyUserDetails;
import com.example.securitydemo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName SecurityConfig
 * @Description: TODO
 * @Author 有丶优秀的少年
 * @Date 2020/10/19
 * @Version V1.0
 **/
@Configuration
@EnableWebSecurity // 启用Spring Security的Web安全支持
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private MyUserDetails myUserDetails;


    /**
     * 这里是设置
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder());
/*        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("123456"))
                .roles("user")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("123456"))
                .roles("admin")
                .and()
                .passwordEncoder(passwordEncoder());*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder：Spring Security 提供的加密工具，可快速实现加密加盐
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //没有权限进入这个页面
       // http.exceptionHandling().accessDeniedPage("/unauth.html");
        //退出登录
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();

        http.formLogin() //这个模式就不需要登录的接口
                //设置前端表单参数的name
                .usernameParameter("name")
                .passwordParameter("pwd")
                //自定义登录页面，不用他的
                .loginPage("/login.html")
        .loginProcessingUrl("/login")
        //.successForwardUrl("/toMain")
                .defaultSuccessUrl("/");
                //前后端分离的使用
               // .successHandler(new MyAuthenticationSuccessHandler("/index.html"))
        //  .failureForwardUrl("/toError");
      //  .failureHandler(new MyAuthenticationFailureHandler("/error.html"));

        http.authorizeRequests()
                //放行 不需要验证
                .antMatchers("/login.html","/login").permitAll()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/index").authenticated()
               // .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .antMatchers("/","/biz1","/biz2").hasAnyAuthority("ROLE_user","ROLE_admin")
                //.antMatchers("/syslog","/sysuser").hasRole("admin")
                .antMatchers("/syslog").hasAuthority("/syslog")
                .antMatchers("/sysuser").hasAuthority("/sysuser")
                //都拦截 有先后顺序
                .anyRequest().authenticated()

        .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                //长时间不操作超时跳到登录
        .invalidSessionUrl("/login.html")
        .sessionFixation().migrateSession()
                //只能一人登录
        .maximumSessions(1)
        .maxSessionsPreventsLogin(false)
        .expiredSessionStrategy(new MyExpiredSessionStrategy());

        http.rememberMe().and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**","/fonts/**","/img/**","/js/**");

    }





}
