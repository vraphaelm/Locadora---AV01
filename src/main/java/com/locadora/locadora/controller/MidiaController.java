package com.locadora.locadora.controller;

import com.locadora.locadora.dto.MidiaRequestDTO;
import com.locadora.locadora.dto.MidiaResponseDTO;
import com.locadora.locadora.service.MidiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midias")
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