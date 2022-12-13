package com.example.jwtauth.configuration;

import com.azure.spring.aad.webapi.AADResourceServerWebSecurityConfigurerAdapter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends AADResourceServerWebSecurityConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .exceptionHandling()
                    .accessDeniedPage("/access-denied")
                    .and()
                    .authorizeRequests(requests -> requests
                            .mvcMatchers(HttpMethod.GET, "/api/**").authenticated()
                            .anyRequest().denyAll())
                    .headers()
                    .contentSecurityPolicy("default-src 'none'");
        }

}
