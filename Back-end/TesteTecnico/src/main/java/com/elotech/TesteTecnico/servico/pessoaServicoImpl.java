package com.elotech.TesteTecnico.servico;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotech.TesteTecnico.modelo.entidade.contatos;
import com.elotech.TesteTecnico.modelo.entidade.pessoa;
import com.elotech.TesteTecnico.modelo.exception.RegraNegocioException;
import com.elotech.TesteTecnico.modelo.repositorio.pessoaRepositorio;

import br.com.caelum.stella.validation.CPFValidator;

@Service
public class pessoaServicoImpl implements IpessoaServico{
	
	private pessoaRepositorio repositorio;
	
	@Autowired
	public pessoaServicoImpl(pessoaRepositorio repositorio) {
		super();
		this.repositorio = repositorio;
	}
	
	public pessoa salvarPessoa(pessoa p) {
		return repositorio.save(p);
	}
	
	@Override
	public boolean validarDataNascimento(String dtNascimento) {
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter data = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate dataAtual = LocalDate.now();
		
		formatoData.setLenient(false);
		try {
			formatoData.parse(dtNascimento);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LocalDate verificandoData = LocalDate.parse(dtNascimento, data);
		
		boolean resultado;
		resultado = verificandoData.compareTo(dataAtual) <= 0;
		
		if(!resultado) 
		{
			throw new RegraNegocioException("A data está no formato errado ou está em uma data futura");
		}
		else 
		{
			return resultado;
		}
	}

	@Override
	public boolean validarCpf(String cpf) {
		
		CPFValidator cpfv = new CPFValidator();
		cpfv.assertValid(cpf);
		
		try {
			cpfv.assertValid(cpf);
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public boolean validaCampos(String nome, String cpf, String dtNascimento) {
		if(nome.isEmpty() || cpf.isEmpty() || dtNascimento.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean validaExistenciaContato(List<contatos> contatos) {
		
		if(contatos.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
}
