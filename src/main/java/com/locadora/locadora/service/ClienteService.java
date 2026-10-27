package com.locadora.locadora.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locadora.locadora.dto.ClienteRequestDTO;
import com.locadora.locadora.dto.ClienteResponseDTO;
import com.locadora.locadora.exception.ResourceNotFoundException;
import com.locadora.locadora.mapper.ClienteMapper;
import com.locadora.locadora.model.Cliente;
import com.locadora.locadora.repository.ClienteRepository;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(ClienteMapper::toDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente não encontrado"));

        return ClienteMapper.toDTO(cliente);
    }

    @org.springframework.transaction.annotation.Transactional
    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente salvo = repository.save(cliente);

        return ClienteMapper.toDTO(salvo);
    }

    @org.springframework.transaction.annotation.Transactional
    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());

        Cliente atualizado = repository.save(cliente);

        return ClienteMapper.toDTO(atualizado);
    }

    @org.springframework.transaction.annotation.Transactional
    public void deletar(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente não encontrado"));

        repository.delete(cliente);
    }
}