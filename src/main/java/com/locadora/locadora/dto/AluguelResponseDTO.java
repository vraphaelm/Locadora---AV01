package com.locadora.locadora.dto;

import java.time.LocalDate;

public class AluguelResponseDTO {

    private Long id;

    private String cliente;

    private String midia;

    private LocalDate dataAluguel;

    private LocalDate dataDevolucao;

    public AluguelResponseDTO(
            Long id,
            String cliente,
            String midia,
            LocalDate dataAluguel,
            LocalDate dataDevolucao) {

        this.id = id;
        this.cliente = cliente;
        this.midia = midia;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public Long getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getMidia() {
        return midia;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}