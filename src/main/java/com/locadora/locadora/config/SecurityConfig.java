package com.locadora.locadora.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.locadora.locadora.security.JWTAuthenticationFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT")
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(
                HttpSecurity http,
                JWTAuthenticationFilter filter,
                AuthenticationProvider provider) throws Exception {

        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/login",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(errors -> errors
                        .authenticationEntryPoint((request, response, exception) -> {
                                response.setStatus(401);
                                response.setContentType("application/json;charset=UTF-8");
                                response.getWriter().write(
                                        "{\"erro\":\"Autenticação necessária ou token inválido\"}");
                        })
                        .accessDeniedHandler((request, response, exception) -> {
                                response.setStatus(403);
                                response.setContentType("application/json;charset=UTF-8");
                                response.getWriter().write(
                                        "{\"erro\":\"Acesso negado\"}");
                        }))
                .authenticationProvider(provider)
                .addFilterBefore(
                        filter, UsernamePasswordAuthenticationFilter.class)
                .build();
        }

        @Bean
        public FilterRegistrationBean<JWTAuthenticationFilter> jwtFilterRegistration(
                JWTAuthenticationFilter filter) {
        FilterRegistrationBean<JWTAuthenticationFilter> registration =
                new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
        }
}