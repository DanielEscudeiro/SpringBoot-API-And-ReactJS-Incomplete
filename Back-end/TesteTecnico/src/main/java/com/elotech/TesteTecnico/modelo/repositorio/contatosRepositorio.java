package com.elotech.TesteTecnico.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elotech.TesteTecnico.modelo.entidade.contatos;

public interface contatosRepositorio extends JpaRepository<contatos, Long>{
	
}
