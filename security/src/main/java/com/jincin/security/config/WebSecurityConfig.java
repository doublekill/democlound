package com.jincin.security.config;

import com.jincin.security.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    CustomUserService customUserService;
    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Override   /**定义认证用户信息获取来源，密码校验规则等*/
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService);
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }

    @Override   /**定义安全策略*/
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/css/*.css").permitAll()
                .antMatchers("/hello").hasRole("ADMIN")//登陆后之后拥有“ADMIN”权限才可以访问/hello ，否则系统会出现“403”权限不足的提示
                .antMatchers("/**").authenticated()
                .and()
                .formLogin() //from表单方式
                .loginPage("/login")//指定登录页是”/login”
                .permitAll()
//                .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .and()
                .logout()
                .logoutSuccessUrl("/login") //退出登录后的默认网址是”/login”
                .permitAll()
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login?error")
                .sessionRegistry(sessionRegistry());


//        http.csrf().disable();

//                .and().rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
//                .tokenValiditySeconds(60 * 60 * 24 * 7);
//        http.sessionManagement()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true)
//

        /**
         * https://www.cnblogs.com/davidwang456/p/4549344.html
         除了authenticated()方法和permitAll()方法外,还有一些其他方法用来定义该如何保护请求.
         access(String) 如果给定的Spring EL表达式计算结果为true，就允许访问
         anonymous() 允许匿名用户访问
         authenticated() 允许认证的用户进行访问
         denyAll() 无条件拒绝所有访问
         fullyAuthenticated() 如果用户是完整认证的话（不是通过Remember-me功能认证的），就允许访问
         hasAuthority(String) 如果用户具备给定权限的话就允许访问
         hasAnyAuthority(String…)如果用户具备给定权限中的某一个的话，就允许访问
         hasRole(String) 如果用户具备给定角色(用户组)的话,就允许访问/
         hasAnyRole(String…) 如果用户具有给定角色(用户组)中的一个的话,允许访问.
         hasIpAddress(String 如果请求来自给定ip地址的话,就允许访问.
         not() 对其他访问结果求反.
         permitAll() 无条件允许访问
         rememberMe() 如果用户是通过Remember-me功能认证的，就允许访问
         */
    }
}
