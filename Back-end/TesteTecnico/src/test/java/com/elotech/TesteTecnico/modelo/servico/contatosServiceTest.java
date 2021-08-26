package com.elotech.TesteTecnico.modelo.servico;

import static org.junit.jupiter.api.Assertions.*;

import javax.mail.internet.AddressException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.elotech.TesteTecnico.modelo.entidade.contatos;
import com.elotech.TesteTecnico.modelo.entidade.pessoa;
import com.elotech.TesteTecnico.modelo.repositorio.contatosRepositorio;
import com.elotech.TesteTecnico.servico.IcontatosServico;
import com.elotech.TesteTecnico.servico.IpessoaServico;
import com.elotech.TesteTecnico.servico.contatosServicoImpl;
import com.elotech.TesteTecnico.servico.pessoaServicoImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test") //Setando o banco de dados h2
public class contatosServiceTest {
	
	//Uso o banco de dados h2 para realizar os testes, 
	//porque não é interessante usar o postgre, pois pode deixar alguma "sujeira"

	@MockBean
	contatosRepositorio repositorio;
	
	IcontatosServico servico;
	
	@BeforeEach
	public void setUp() {
		servico = new contatosServicoImpl(repositorio);
	}
	
	@Test
	public void validaEmailDigitosCorretos() {
		
		String email = "dancaescudeiro@gmail.com";
		contatos c = contatos.builder().nome("Daniel").telefone("(11) 97566-7417").email(email).build();
		repositorio.save(c);
		
		boolean resultado = servico.validarEmail(email);
			
		Assertions.assertTrue(resultado);
		
	}
	
	@Test
	public void validaEmailDigitosErrados() {
		
		String email = "danca@r@gmail.com";
		contatos c = contatos.builder().nome("Daniel").telefone("(11) 97566-7417").email(email).build();
		repositorio.save(c);
		
		boolean resultado = servico.validarEmail(email);
		
		Assertions.assertFalse(resultado);
	}
	
	@Test
	public void validaSeTodosOsCamposEstaoPreenchidosContatos() {
		
		String nome = "";
		String email = "";
		String telefone = "";
		
		contatos c = contatos.builder().nome(nome).telefone(telefone).email(email).build();
		repositorio.save(c);
		
		boolean resultado = servico.validaCampos(nome, telefone, email);
		
		Assertions.assertTrue(resultado);
		
	}
}
