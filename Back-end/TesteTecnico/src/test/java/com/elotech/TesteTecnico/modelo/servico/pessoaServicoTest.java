package com.elotech.TesteTecnico.modelo.servico;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.elotech.TesteTecnico.modelo.entidade.contatos;
import com.elotech.TesteTecnico.modelo.entidade.pessoa;
import com.elotech.TesteTecnico.modelo.exception.RegraNegocioException;
import com.elotech.TesteTecnico.modelo.repositorio.pessoaRepositorio;
import com.elotech.TesteTecnico.servico.pessoaServicoImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test") //Setando o banco de dados h2
public class pessoaServicoTest {
	
	//Uso o banco de dados h2 para realizar os testes, 
	//porque não é interessante usar o postgre, pois pode deixar alguma "sujeira"
	
	@MockBean
	pessoaRepositorio repositorio;
	
	@SpyBean
	pessoaServicoImpl servico;
	
	@Test
	public void salvarPessoa() {
		
		pessoa p = pessoa.builder().nome("Daniel").cpf("525.397.668-03").dataNascimento("22/05/2002").build();
		
		Mockito.when(repositorio.save(Mockito.any(pessoa.class))).thenReturn(p);	
		
		pessoa pSalvo = servico.salvarPessoa(new pessoa());
		
		pessoa pessoaSalva = servico.salvarPessoa(p);
		
		Assertions.assertNotNull(pessoaSalva);
		
	}
	
	@Test
	public void naoSalvaEnquantoNaoTiverUmContato() {
		
		List<contatos> contatos = new ArrayList<>();
		pessoa p = pessoa.builder().nome("Daniel").cpf("525.397.668-03").dataNascimento("22/05/2002").contato(contatos).build();
		repositorio.save(p);
		
		boolean resultado = servico.validaExistenciaContato(contatos);
		
		Assertions.assertFalse(resultado);
	}
	
	@Test
	public void validaSeTodosOsCamposEstaoPreenchidosPessoa() {
		
		String nome = "";
		String cpf = "";
		String dtNascimento = "";
		
		pessoa p = pessoa.builder().nome(nome).cpf(cpf).dataNascimento(dtNascimento).build();
		repositorio.save(p);
		
		boolean resultado = servico.validaCampos(nome, cpf, dtNascimento);
		
		Assertions.assertTrue(resultado);
		
	}
	
	@Test
	public void verificaDataFutura() {
		
		String dataNascimento = "22/05/2002";
		pessoa p = pessoa.builder().nome("Daniel").cpf("525.397.668-03").dataNascimento(dataNascimento).build();
		repositorio.save(p);
		
		boolean resultado = servico.validarDataNascimento(dataNascimento);
			
		Assertions.assertTrue(resultado);
	}
	
	@Test
	public void verificaDataFuturaQuandoForDataFutura() {
		
		String dataNascimento = "22/05/2022";
		pessoa p = pessoa.builder().nome("Daniel").cpf("525.397.668-03").dataNascimento(dataNascimento).build();
		repositorio.save(p);
		
		Assertions.assertThrows(RegraNegocioException.class, () -> servico.validarDataNascimento(dataNascimento));
	}
	
	@Test
	public void validaCpfDigitosCorretos() {
		
		String cpf = "525.397.668-03";
		pessoa p = pessoa.builder().nome("Daniel").cpf(cpf).dataNascimento("22/05/2002").build();
		repositorio.save(p);
		
		boolean resultado = servico.validarCpf(cpf);
			
		Assertions.assertTrue(resultado);
	}
	
	@Test
	public void validaCpfDigitosErrados() {
		
		String cpf = "525.397.66803";
		pessoa p = pessoa.builder().nome("Daniel").cpf(cpf).dataNascimento("22/05/2002").build();
		repositorio.save(p);
		
		
		Assertions.assertThrows(Exception.class, () -> servico.validarCpf(cpf));
	}
}
