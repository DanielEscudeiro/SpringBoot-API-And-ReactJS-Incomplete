package com.elotech.TesteTecnico.modelo.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "pessoa", schema = "testetecnicoelotech") //Especificando o nome da tabela e o schema para facilitar o entendimento
@Data //Usando Lombok para deixar o código mais clean.
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class pessoa {
	
	//Especificando o nome da coluna para facilitar o entendimento
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "cpf", nullable = false)
	private String cpf;
	
	@Column(name = "datanascimento", nullable = false)
	private String dataNascimento;
	
	@JsonIgnoreProperties("pessoa")
	@OneToMany(mappedBy = "Pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<contatos> contato = new ArrayList<>();
	
}
