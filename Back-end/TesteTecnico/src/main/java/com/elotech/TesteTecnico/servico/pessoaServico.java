package com.elotech.TesteTecnico.servico;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotech.TesteTecnico.modelo.entidade.pessoa;
import com.elotech.TesteTecnico.modelo.exception.PessoaNaoEncontradaException;
import com.elotech.TesteTecnico.modelo.repositorio.pessoaRepositorio;

@Service
public class pessoaServico {
	
	private pessoaRepositorio PessoaRepositorio;
	
	@Autowired
	public pessoaServico(pessoaRepositorio pessoaRepositorio) {
		PessoaRepositorio = pessoaRepositorio;
	}
	
	//Método Get para retornar todos os registros
	public List<pessoa> listarPessoas(){
		return PessoaRepositorio.findAll();
	}
	
	//Método Get para retornar uma pessoa em especifico
	public pessoa getPessoa(Long id) {
		return findOrFail(id);
	}
	
	//Construção do método para retornar uma pessoa em especifico
	private pessoa findOrFail(Long id) {
		return PessoaRepositorio.findById(id).orElseThrow(() -> new PessoaNaoEncontradaException("A Pessoa não foi localizada, tente novamente com outro id."));
	}
	
	//Método Post
	public pessoa gravarPessoaBD(pessoa p) {
		p.getContato().forEach(c -> c.setPessoa(p)); //Para cada contato vai inserir uma pessoa
		return PessoaRepositorio.save(p);
	}
	
	//Método Put
	public pessoa alterarPessoaBD(Long id, pessoa p) {
		pessoa registroPessoaSalva = findOrFail(id);
		
		registroPessoaSalva.getContato().clear();
		registroPessoaSalva.getContato().addAll(p.getContato());
		BeanUtils.copyProperties(p, registroPessoaSalva, "id", "contato"); //Vai copiar as propriedades de p para registroPessoaSalva, ele não vai copiar o id e nem a parte de contatos, pois já está tratado
		return gravarPessoaBD(registroPessoaSalva);//PessoaRepositorio.save(registroPessoaSalva);
	}
	
	//Método Delete
	public void deletarPessoaBD(Long id) {
		pessoa p = findOrFail(id);
		PessoaRepositorio.delete(p);
	}
}
