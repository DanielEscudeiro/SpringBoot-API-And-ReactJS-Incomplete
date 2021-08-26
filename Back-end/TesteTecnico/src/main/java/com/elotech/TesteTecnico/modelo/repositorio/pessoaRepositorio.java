package com.elotech.TesteTecnico.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elotech.TesteTecnico.modelo.entidade.pessoa;

public interface pessoaRepositorio extends JpaRepository<pessoa, Long>{

	
}
