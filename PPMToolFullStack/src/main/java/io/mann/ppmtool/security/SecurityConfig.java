package io.mann.ppmtool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @Author: Mann
 * @Date: 2019-06-03 20:39
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
//      开启@Secured 注解过滤权限
        securedEnabled = true,
//      开启@RolesAllowed 注解过滤权限
        jsr250Enabled = true,
        /**
         * 使用表达式时间方法级别的安全性 4个注解可用
         * <li>@PreAuthorize 在方法调用之前, 基于表达式的计算结果来限制对方法的访问</li>
         * <li>@PostAuthorize 允许方法调用, 但是如果表达式计算结果为false, 将抛出一个安全性异常</li>
         * <li>@PostFilter 允许方法调用, 但必须按照表达式来过滤方法的结果</li>
         * <li>@PreFilter 允许方法调用, 但必须在进入方法之前过滤输入值</li>
        */
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().sameOrigin() // To enable H2 Database
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/favicon.ico",
                        "/**/*png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/api/users/**").permitAll()
                .anyRequest().authenticated();

    }
}
