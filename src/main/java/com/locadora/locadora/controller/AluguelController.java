package com.locadora.locadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.locadora.dto.AluguelRequestDTO;
import com.locadora.locadora.dto.AluguelResponseDTO;
import com.locadora.locadora.service.AluguelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    private final AluguelService service;

    public AluguelController(
            AluguelService service) {

        this.service = service;
    }

    @GetMapping
    public List<AluguelResponseDTO> listar() {

        return service.listar();
    }

    @GetMapping("/{id}")
    public AluguelResponseDTO buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AluguelResponseDTO criar(
            @RequestBody @Valid
            AluguelRequestDTO dto) {

        return service.criar(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(
            @PathVariable Long id) {

        service.deletar(id);
    }
}
