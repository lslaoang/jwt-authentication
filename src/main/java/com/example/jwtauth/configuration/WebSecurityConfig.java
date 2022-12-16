package com.example.jwtauth.configuration;

import com.azure.spring.aad.webapi.AADResourceServerWebSecurityConfigurerAdapter;
import com.example.jwtauth.handler.JwtAccessDeniedHandler;
import com.example.jwtauth.handler.JwtAuthenticationEntryPoint;
import com.example.jwtauth.handler.JwtAuthenticationFailureFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends AADResourceServerWebSecurityConfigurerAdapter {

    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthenticationFailureFilter authenticationFailureFilter;

    public WebSecurityConfig(JwtAccessDeniedHandler jwtAccessDeniedHandler,
                             JwtAuthenticationEntryPoint authenticationEntryPoint,
                             JwtAuthenticationFailureFilter authenticationFailureFilter) {
        this.accessDeniedHandler = jwtAccessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFailureFilter = authenticationFailureFilter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler) /*** customized access-denied handler **/
                .authenticationEntryPoint(authenticationEntryPoint) /** before reaching controller, this will validate the authentication first **/
                .and()
                .addFilter(authenticationFailureFilter.bearerTokenAuthenticationFilter(authenticationManagerBean())) /*** customizing the responses if failed authentication **/
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(requests -> requests
                        .mvcMatchers(HttpMethod.GET, "/api/access-denied").anonymous()
                        .mvcMatchers(HttpMethod.GET,"/api/**").authenticated()
                        .mvcMatchers(HttpMethod.POST,"/api/**").authenticated()
                        .anyRequest().denyAll())
                .headers()
                .contentSecurityPolicy("default-src 'none'");
    }
}
