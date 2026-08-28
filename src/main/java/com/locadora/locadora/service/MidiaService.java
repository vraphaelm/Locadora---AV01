package com.locadora.locadora.service;

import com.locadora.locadora.dto.MidiaRequestDTO;
import com.locadora.locadora.dto.MidiaResponseDTO;
import com.locadora.locadora.exception.ResourceNotFoundException;
import com.locadora.locadora.mapper.MidiaMapper;
import com.locadora.locadora.model.Midia;
import com.locadora.locadora.repository.MidiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MidiaService {

    private final MidiaRepository repository;

    public MidiaService(MidiaRepository repository) {
        this.repository = repository;
    }

    public List<MidiaResponseDTO> listar() {

        return repository.findAll()
                .stream()
                .map(MidiaMapper::toDTO)
                .toList();
    }

    public MidiaResponseDTO buscarPorId(Long id) {

        Midia midia = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mídia não encontrada"));

        return MidiaMapper.toDTO(midia);
    }

    public MidiaResponseDTO criar(MidiaRequestDTO dto) {

        Midia midia = MidiaMapper.toEntity(dto);

        Midia salva = repository.save(midia);

        return MidiaMapper.toDTO(salva);
    }

    public MidiaResponseDTO atualizar(Long id, MidiaRequestDTO dto) {

        Midia midia = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mídia não encontrada"));

        midia.setTitulo(dto.getTitulo());
        midia.setTipo(dto.getTipo());
        midia.setAno(dto.getAno());
        midia.setQuantidade(dto.getQuantidade());

        Midia atualizada = repository.save(midia);

        return MidiaMapper.toDTO(atualizada);
    }

    public void deletar(Long id) {

        Midia midia = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mídia não encontrada"));

        repository.delete(midia);
    }
}