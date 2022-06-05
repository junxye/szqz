package com.szqz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * SpringSecurity 核心配置类
 * prePostEnabled = true 开启注解权限认证功能
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启@PreAuthorize()注解权限功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //  @Autowired
 //   private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;  //认证过滤器

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;  // 认证失败处理器

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;    // 授权失败处理器

    @Autowired
    AuthenticationSuccess authenticationSuccess;    //登录成功

    @Autowired
    AuthenticationFailure authenticationFailure;    //登录失败

    @Autowired
    AuthenticationEnryPoint authenticationEnryPoint;    //未登录

    @Autowired
    AuthenticationLogout authenticationLogout;  //注销

    @Autowired
    SelfAuthenticationProvider selfAuthenticationProvider;  //自定义认证逻辑处理

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(selfAuthenticationProvider);
    }

    /**
     * 密码机密处理器
     * <p>
     * 将BCryptPasswordEncoder对象注入到spring容器中,更换掉原来的 PasswordEncoder加密方式
     * 原PasswordEncoder密码格式为：{id}password。它会根据id去判断密码的加密方式。
     * 如果没替换原来的加密方式，数据库中想用明文密码做测试，将密码字段改为{noop}123456这样的格式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证配置
     * anonymous()：匿名访问：未登录可以访问，已登录不能访问
     * permitAll()：有没有认证都能访问：登录或未登录都能访问
     * denyAll(): 拒绝
     * authenticated()：认证之后才能访问
     * hasAuthority（）：包含权限
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //cors()解决跨域问题，csrf()会与restful风格冲突，默认springsecurity是开启的，所以要disable()关闭一下
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/index").hasRole("USER")
                .antMatchers("/hello","/manager/*").hasRole("ADMIN")


                .and()
                .formLogin()  //开启登录
                .permitAll()  //允许所有人访问
                .successHandler(authenticationSuccess) // 登录成功逻辑处理
                .failureHandler(authenticationFailure) // 登录失败逻辑处理

                .and()
                .logout()   //开启注销
                .permitAll()    //允许所有人访问
                .logoutSuccessHandler(authenticationLogout) //注销逻辑处理
                .deleteCookies("JSESSIONID")    //删除cookie

                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)    //权限不足的时候的逻辑处理
                .authenticationEntryPoint(authenticationEnryPoint);  //未登录时的逻辑处理

        // 添加token过滤器
 //       http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置异常处理器
        http.exceptionHandling()
                // 认证失败
                .authenticationEntryPoint(authenticationEntryPoint)
                // 授权失败
                .accessDeniedHandler(accessDeniedHandler)

                .and()
                .sessionManagement()
                .maximumSessions(1)     //最多只能一个用户登录一个账号
  //              .expiredSessionStrategy(sessionInformationExpiredStrategy)  //异地登录的逻辑处理
        ;


    }

    /**
     * 注入AuthenticationManager 进行用户认证
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
