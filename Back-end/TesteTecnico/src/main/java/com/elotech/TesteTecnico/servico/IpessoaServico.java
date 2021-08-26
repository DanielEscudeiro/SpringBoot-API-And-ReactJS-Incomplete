package com.elotech.TesteTecnico.servico;

import java.util.List;

import com.elotech.TesteTecnico.modelo.entidade.contatos;
import com.elotech.TesteTecnico.modelo.entidade.pessoa;

public interface IpessoaServico {
	
	boolean validarDataNascimento(String dtNascimento);
	
	boolean validarCpf(String cpf);
	
	boolean validaCampos(String nome, String cpf, String dtNascimento);
	
	boolean validaExistenciaContato(List<contatos> contatos);
	
	pessoa salvarPessoa(pessoa p);
}
