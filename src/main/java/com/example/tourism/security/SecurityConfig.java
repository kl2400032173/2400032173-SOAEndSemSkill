
package com.example.tourism.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class SecurityConfig {

    @Bean
   public SecurityFilterChain securityFilterChain(
            HttpSecurity http, JwtFilter jwtFilter)
            throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.sessionManagement(session ->
            session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
            )
        );

        http.authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**"
            ).permitAll()
            .anyRequest().authenticated()
        );

        http.addFilterBefore(
            jwtFilter,
            UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> disableServletFilter(
            JwtFilter jwtFilter) {

        FilterRegistrationBean<JwtFilter> registration =
            new FilterRegistrationBean<>(jwtFilter);

        registration.setEnabled(false);
        return registration;
    }
}
