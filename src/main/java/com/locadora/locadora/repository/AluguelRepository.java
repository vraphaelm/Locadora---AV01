package com.locadora.locadora.repository;

import com.locadora.locadora.model.Aluguel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository
        extends JpaRepository<Aluguel, Long> {

}