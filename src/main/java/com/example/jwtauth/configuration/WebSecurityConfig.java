package com.example.jwtauth.configuration;


import com.azure.spring.aad.webapi.AADResourceServerWebSecurityConfigurerAdapter;
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
                .accessDeniedPage("/access-denied").and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(requests -> requests
//                        .mvcMatchers(HttpMethod.GET, "/").anonymous()
//                        .mvcMatchers(HttpMethod.GET, "/api/**").authenticated()
//                        .mvcMatchers(HttpMethod.POST, "/api/other").hasAnyRole("role")
                        .anyRequest().authenticated())
                .headers()
                .contentSecurityPolicy("default-src 'none'");
    }
}
