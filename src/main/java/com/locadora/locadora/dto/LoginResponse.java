
package com.locadora.locadora.dto;

public record LoginResponse(
        String token,
        String tipo,
        String email,
        String perfil) {
}