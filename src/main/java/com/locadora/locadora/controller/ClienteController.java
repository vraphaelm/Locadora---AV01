package com.locadora.locadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.locadora.dto.ClienteRequestDTO;
import com.locadora.locadora.dto.ClienteResponseDTO;
import com.locadora.locadora.service.ClienteService;

import jakarta.validation.Valid;

@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criar(
            @RequestBody @Valid ClienteRequestDTO dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ClienteRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}