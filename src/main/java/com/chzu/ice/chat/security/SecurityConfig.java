package com.chzu.ice.chat.config;

import com.chzu.ice.chat.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author mason
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/userAccount/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(tokenAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
        http.headers().cacheControl().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter tokenAuthenticationFilterBean() {
        return new JwtAuthFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 未登录调用需要登录使用的接口
     **/
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write("{\"error\":\"请登录\"}");
            httpServletResponse.setStatus(401);
        };
    }
}
