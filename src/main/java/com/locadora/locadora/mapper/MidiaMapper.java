package com.locadora.locadora.mapper;

import com.locadora.locadora.dto.MidiaRequestDTO;
import com.locadora.locadora.dto.MidiaResponseDTO;
import com.locadora.locadora.model.Midia;

public class MidiaMapper {

    public static Midia toEntity(MidiaRequestDTO dto) {

        Midia midia = new Midia();

        midia.setTitulo(dto.getTitulo());
        midia.setTipo(dto.getTipo());
        midia.setAno(dto.getAno());
        midia.setQuantidade(dto.getQuantidade());

        return midia;
    }

    public static MidiaResponseDTO toDTO(Midia midia) {

        return new MidiaResponseDTO(
                midia.getId(),
                midia.getTitulo(),
                midia.getTipo(),
                midia.getAno(),
                midia.getQuantidade()
        );
    }
}