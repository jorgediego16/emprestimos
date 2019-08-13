package com.justa.emprestimos.models.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = -3162451461666556342L;

	public PessoaDTO() {

	}

	public PessoaDTO(Long id, String nome, String obs, String palavraChave, long idUsuarioCriador, boolean pessoaFisica, String cpfOuCnpj) {
		this.id = id;
		this.nome = nome;
		this.obs = obs;
		this.palavraChave = palavraChave;
		this.idUsuarioCriador = idUsuarioCriador;
		this.pessoaFisica = pessoaFisica;
		this.cpfOuCnpj = cpfOuCnpj;
	}

	private Long id;
	
	private String nome;

	private String obs;

	private String palavraChave;

	private long idUsuarioCriador;

	private boolean pessoaFisica;
	
	private String cpfOuCnpj;
}
