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

import com.locadora.locadora.dto.MidiaRequestDTO;
import com.locadora.locadora.dto.MidiaResponseDTO;
import com.locadora.locadora.service.MidiaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/midias")
@SecurityRequirement(name = "bearerAuth")
public class MidiaController {

    private final MidiaService service;

    public MidiaController(MidiaService service) {
        this.service = service;
    }

    @GetMapping
    public List<MidiaResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public MidiaResponseDTO buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MidiaResponseDTO criar(
            @RequestBody @Valid MidiaRequestDTO dto) {

        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public MidiaResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid MidiaRequestDTO dto) {

        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(
            @PathVariable Long id) {

        service.deletar(id);
    }
}