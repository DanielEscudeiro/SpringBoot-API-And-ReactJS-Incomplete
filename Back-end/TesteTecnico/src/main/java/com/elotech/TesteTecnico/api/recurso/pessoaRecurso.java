package com.elotech.TesteTecnico.api.recurso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elotech.TesteTecnico.modelo.entidade.pessoa;
import com.elotech.TesteTecnico.servico.pessoaServico;
import com.elotech.TesteTecnico.servico.pessoaServicoImpl;

@RestController
@RequestMapping(path = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class pessoaRecurso {
	
	private pessoaServico servico;
	private pessoaServicoImpl servicoImpl;
	
	@Autowired
	public pessoaRecurso(pessoaServico servico, pessoaServicoImpl servicoImpl) {
		this.servico = servico;
		this.servicoImpl = servicoImpl;
	}
	
	@GetMapping //busca todas as pessoas cadastradas
	public List<pessoa> listaPessoas() {
		return servico.listarPessoas();
	}
	
	@GetMapping("/{id}") //retorna uma pessoa de 1 id em especifico
	public ResponseEntity<pessoa> buscaPessoa(@PathVariable Long id){
		return ResponseEntity.ok(servico.getPessoa(id)); //Volta o valor 200 na tela
	}
	
	@PostMapping //Grava a pessoa no banco
	public ResponseEntity<pessoa> salvaPessoa(@RequestBody pessoa p){
		
		boolean verificaCamposVazios = servicoImpl.validaCampos(p.getNome(), p.getCpf(), p.getDataNascimento());
		boolean validaExistenciaContato = servicoImpl.validaExistenciaContato(p.getContato());
		boolean validaCpf =  servicoImpl.validarCpf(p.getCpf());
		boolean validaDataNascimento = servicoImpl.validarDataNascimento(p.getDataNascimento());
		
		if(validaCpf == true && validaDataNascimento == true && verificaCamposVazios == false && validaExistenciaContato == true) {
			pessoa Pessoa = servico.gravarPessoaBD(p);
			return ResponseEntity.status(HttpStatus.CREATED).body(Pessoa);
		}
		else {
			return ResponseEntity.badRequest().body(p);
		}
	}
	
	@PutMapping("/{id}") //Altera o dado da pessoa no banco
	public ResponseEntity<pessoa> alteraPessoa(@PathVariable Long id, @RequestBody pessoa p){
		
		boolean verificaCamposVazios = servicoImpl.validaCampos(p.getNome(), p.getCpf(), p.getDataNascimento());
		boolean validaExistenciaContato = servicoImpl.validaExistenciaContato(p.getContato());
		boolean validaCpf = servicoImpl.validarCpf(p.getCpf());
		boolean validaDataNascimento = servicoImpl.validarDataNascimento(p.getDataNascimento());
		
		if(validaCpf == true && validaDataNascimento == true && verificaCamposVazios == false && validaExistenciaContato == true){
			return ResponseEntity.ok(servico.alterarPessoaBD(id, p));
		}
		else {
			return ResponseEntity.badRequest().body(p);
		}
		
		
	}
	
	@DeleteMapping("/{id}") //Deleta o dado da pessoa no banco
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaPessoa(@PathVariable Long id) {
		servico.deletarPessoaBD(id);
	}
}
