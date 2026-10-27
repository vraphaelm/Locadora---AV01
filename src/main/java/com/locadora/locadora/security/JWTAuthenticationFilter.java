package com.locadora.locadora.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.locadora.locadora.service.impl.JWTService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService usuarioDetailsService;

    public JWTAuthenticationFilter(
            JWTService jwtService,
            UserDetailsService usuarioDetailsService) {
        this.jwtService = jwtService;
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String cabecalho = request.getHeader("Authorization");

        if (cabecalho == null || !cabecalho.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = cabecalho.substring(7);

        try {
            String email = jwtService.extractUsername(token);

            if (email != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails usuario =
                        usuarioDetailsService.loadUserByUsername(email);

                if (jwtService.isTokenValid(token, usuario)) {
                    UsernamePasswordAuthenticationToken autenticacao =
                            new UsernamePasswordAuthenticationToken(
                                    usuario, null, usuario.getAuthorities());

                    autenticacao.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request));

                    SecurityContextHolder.getContext()
                            .setAuthentication(autenticacao);
                }
            }
        } catch (JwtException | IllegalArgumentException e) {
            SecurityContextHolder.clearContext();
        } catch (org.springframework.security.core.userdetails.UsernameNotFoundException e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}