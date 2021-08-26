package com.elotech.TesteTecnico.servico;

public interface IcontatosServico {
	
	boolean validarEmail(String email);
	
	boolean validaCampos(String nome, String telefone, String email);
}
