package com.locadora.locadora.mapper;

import com.locadora.locadora.dto.ClienteRequestDTO;
import com.locadora.locadora.dto.ClienteResponseDTO;
import com.locadora.locadora.model.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteRequestDTO dto) {

        Cliente cliente = new Cliente();

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());

        return cliente;
    }

    public static ClienteResponseDTO toDTO(Cliente cliente) {

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}