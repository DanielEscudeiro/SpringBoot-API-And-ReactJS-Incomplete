package com.elotech.TesteTecnico.servico;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotech.TesteTecnico.modelo.repositorio.contatosRepositorio;

@Service
public class contatosServicoImpl implements IcontatosServico{
	
private contatosRepositorio repositorio;
	
	@Autowired
	public contatosServicoImpl(contatosRepositorio repositorio) {
		super();
		this.repositorio = repositorio;
	}
	
	@Override
	public boolean validarEmail(String email) {
		boolean validacao = true;
		
        try {
            InternetAddress enderecoEmail = new InternetAddress(email);
            enderecoEmail.validate();
            
        } catch (AddressException ex) {
        	validacao = false;
        }
        return validacao;
	}
	
	@Override
	public boolean validaCampos(String nome, String telefone, String email) {
		
		if(nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
}