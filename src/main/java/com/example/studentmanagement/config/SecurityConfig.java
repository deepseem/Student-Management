package com.example.studentmanagement.config;



import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.*;


@Configuration
public class SecurityConfig {

    /*private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {

        this.jwtFilter = jwtFilter;
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        /*http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();*/

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().permitAll());

        return http.build();
    }
}
