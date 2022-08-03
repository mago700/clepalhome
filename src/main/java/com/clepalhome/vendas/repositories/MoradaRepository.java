package com.clepalhome.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clepalhome.vendas.domain.Morada;

@Repository
public interface MoradaRepository extends JpaRepository<Morada, Integer>{

}
