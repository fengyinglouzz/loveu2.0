package com.zxzyyyy.loveu.config;

import com.zxzyyyy.loveu.filter.AuthenticationTokenFilter;
import com.zxzyyyy.loveu.handler.EntryPointUnauthorizedHandler;
import com.zxzyyyy.loveu.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description:
 */
@Configuration      // 声明为配置类
@EnableWebSecurity      // 启用 Spring Security web 安全的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注册 token 转换拦截器为 bean
     * 如果客户端传来了 token ，那么通过拦截器解析 token 赋予用户权限
     *
     * @return
     * @throws Exception
     */
    /**
     * 注册 401 处理器
     */
    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;

    /**
     * 注册 403 处理器
     */
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/test").authenticated()       // 需携带有效 token
                .antMatchers("/test/*").hasAuthority("admin")   // 需拥有 admin 这个权限
//                .antMatchers("/test/*").hasRole("ADMIN")      需拥有 ADMIN 这个身份
                // 配置被拦截时的处理
                .anyRequest().permitAll()       // 允许所有请求通过
                .and()
                .csrf()
                .disable()                      // 禁用 Spring Security 自带的跨域处理
                .sessionManagement()                        // 定制我们自己的 session 策略
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 调整为让 Spring Security 不创建和使用 session


        /**
         * 本次 json web token 权限控制的核心配置部分
         * 在 Spring Security 开始判断本次会话是否有权限时的前一瞬间
         * 通过添加过滤器将 token 解析，将用户所有的权限写入本次 Spring Security 的会话
         */
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        http
                .exceptionHandling()
                .authenticationEntryPoint(this.unauthorizedHandler)   // 添加 token 无效或者没有携带 token 时的处理
                .accessDeniedHandler(this.accessDeniedHandler);     //添加无权限时的处理

    }
}