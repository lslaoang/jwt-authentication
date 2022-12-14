package com.example.jwtauth.configuration;


import com.azure.spring.aad.webapi.AADResourceServerWebSecurityConfigurerAdapter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends AADResourceServerWebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .exceptionHandling()
                .accessDeniedPage("/api/access-denied").and()
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
