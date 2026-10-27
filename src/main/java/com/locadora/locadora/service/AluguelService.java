package com.locadora.locadora.service;

import com.locadora.locadora.dto.AluguelRequestDTO;
import com.locadora.locadora.dto.AluguelResponseDTO;
import com.locadora.locadora.exception.ResourceNotFoundException;
import com.locadora.locadora.model.Aluguel;
import com.locadora.locadora.model.Cliente;
import com.locadora.locadora.model.Midia;
import com.locadora.locadora.mapper.AluguelMapper;
import com.locadora.locadora.repository.AluguelRepository;
import com.locadora.locadora.repository.ClienteRepository;
import com.locadora.locadora.repository.MidiaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final ClienteRepository clienteRepository;
    private final MidiaRepository midiaRepository;

    public AluguelService(
            AluguelRepository aluguelRepository,
            ClienteRepository clienteRepository,
            MidiaRepository midiaRepository) {

        this.aluguelRepository = aluguelRepository;
        this.clienteRepository = clienteRepository;
        this.midiaRepository = midiaRepository;
    }

    public List<AluguelResponseDTO> listar() {
        return aluguelRepository.findAll()
                .stream()
                .map(AluguelMapper::toDTO)
                .toList();
    }

    public AluguelResponseDTO buscarPorId(Long id) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Aluguel não encontrado"));

        return AluguelMapper.toDTO(aluguel);
    }

    @org.springframework.transaction.annotation.Transactional
    public AluguelResponseDTO criar(AluguelRequestDTO dto) {

        if (dto.getDataDevolucao().isBefore(dto.getDataAluguel())) {
            throw new IllegalArgumentException(
                    "Data de devolução não pode ser anterior ao aluguel");
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente não encontrado"));

        Midia midia = midiaRepository.findById(dto.getMidiaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mídia não encontrada"));

        Aluguel aluguel = new Aluguel();

        aluguel.setCliente(cliente);
        aluguel.setMidia(midia);
        aluguel.setDataAluguel(dto.getDataAluguel());
        aluguel.setDataDevolucao(dto.getDataDevolucao());

        Aluguel salvo = aluguelRepository.save(aluguel);

        return AluguelMapper.toDTO(salvo);
    }

    @org.springframework.transaction.annotation.Transactional
    public void deletar(Long id) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Aluguel não encontrado"));

        aluguelRepository.delete(aluguel);
    }
}