package com.elotech.TesteTecnico.modelo.entidade;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contatos", schema = "testetecnicoelotech") //Especificando o nome da tabela e o schema para facilitar o entendimento
@Data //Usando Lombok para deixar o código mais clean.
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class contatos {
	
	//Especificando o nome da coluna para facilitar o entendimento
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Column(name = "email", nullable = false)
	private String email; 
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private pessoa Pessoa;
}
