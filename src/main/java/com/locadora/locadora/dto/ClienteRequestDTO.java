package com.locadora.locadora.dto;

import jakarta.validation.constraints.NotBlank;

public class ClienteRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @jakarta.validation.constraints.Email
    private String email;

    @NotBlank
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}