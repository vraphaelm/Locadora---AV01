package com.locadora.locadora.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AluguelRequestDTO {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long midiaId;

    @NotNull
    private LocalDate dataAluguel;

    @NotNull
    private LocalDate dataDevolucao;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getMidiaId() {
        return midiaId;
    }

    public void setMidiaId(Long midiaId) {
        this.midiaId = midiaId;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}