package com.locadora.locadora.service;

import com.locadora.locadora.dto.AluguelRequestDTO;
import com.locadora.locadora.dto.AluguelResponseDTO;
import com.locadora.locadora.exception.ResourceNotFoundException;
import com.locadora.locadora.model.Aluguel;
import com.locadora.locadora.model.Cliente;
import com.locadora.locadora.model.Midia;
import com.locadora.locadora.repository.AluguelRepository;
import com.locadora.locadora.repository.ClienteRepository;
import com.locadora.locadora.repository.MidiaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
                .map(this::converterParaDTO)
                .toList();
    }

    public AluguelResponseDTO buscarPorId(Long id) {

        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Aluguel não encontrado"
                        )
                );

        return converterParaDTO(aluguel);
    }

    public AluguelResponseDTO criar(
            AluguelRequestDTO dto) {

        Cliente cliente = clienteRepository
                .findById(dto.getClienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cliente não encontrado"
                        )
                );

        Midia midia = midiaRepository
                .findById(dto.getMidiaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Mídia não encontrada"
                        )
                );

        Aluguel aluguel = new Aluguel();

        aluguel.setCliente(cliente);
        aluguel.setMidia(midia);
        aluguel.setDataAluguel(dto.getDataAluguel());
        aluguel.setDataDevolucao(dto.getDataDevolucao());

        Aluguel salvo =
                aluguelRepository.save(aluguel);

        return converterParaDTO(salvo);
    }

    public void deletar(Long id) {

        Aluguel aluguel = aluguelRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Aluguel não encontrado"
                        )
                );

        aluguelRepository.delete(aluguel);
    }

    private AluguelResponseDTO converterParaDTO(
            Aluguel aluguel) {

        return new AluguelResponseDTO(
                aluguel.getId(),
                aluguel.getCliente().getNome(),
                aluguel.getMidia().getTitulo(),
                aluguel.getDataAluguel(),
                aluguel.getDataDevolucao()
        );
    }
}