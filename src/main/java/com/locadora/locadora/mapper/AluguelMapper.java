package com.locadora.locadora.mapper;

import com.locadora.locadora.dto.AluguelResponseDTO;
import com.locadora.locadora.model.Aluguel;

public final class AluguelMapper {

    private AluguelMapper() {
    }

    public static AluguelResponseDTO toDTO(Aluguel aluguel) {
        return new AluguelResponseDTO(
                aluguel.getId(),
                aluguel.getCliente().getNome(),
                aluguel.getMidia().getTitulo(),
                aluguel.getDataAluguel(),
                aluguel.getDataDevolucao());
    }
}