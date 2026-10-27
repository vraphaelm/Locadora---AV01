package com.locadora.locadora.service;

import com.locadora.locadora.dto.LoginRequest;
import com.locadora.locadora.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}