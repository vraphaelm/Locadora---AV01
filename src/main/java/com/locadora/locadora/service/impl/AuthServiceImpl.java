package com.locadora.locadora.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.locadora.locadora.dto.LoginRequest;
import com.locadora.locadora.dto.LoginResponse;
import com.locadora.locadora.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(LoginRequest dados) {
        Authentication autenticacao = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dados.email(), dados.senha()));

        UserDetails usuario = (UserDetails) autenticacao.getPrincipal();

        String perfil = usuario.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(autoridade -> autoridade.startsWith("ROLE_"))
                .map(autoridade -> autoridade.substring(5).toLowerCase())
                .findFirst()
                .orElse("user");

        String token = jwtService.generateToken(usuario);

        return new LoginResponse(
                token,
                "Bearer",
                usuario.getUsername(),
                perfil);
    }
}