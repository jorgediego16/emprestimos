package com.justa.emprestimos.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class PessoaJuridicaDTO implements Serializable {

	private static final long serialVersionUID = -6022440930844992677L;

	public PessoaJuridicaDTO() {

	}

	private Long id;
	
	private String nome;

	private String obs;

	private String palavraChave;

	private Long idConsigna;

	private Long idEmpresaTelugo;

	private long idUsuarioCriador;
	
	private String razaoSocial;

	private String cnpj;

	private String cgf;

	private String atividade;

	private String atividadeSecundaria;

	private Double faturamento;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fundacao;

	private Integer quantidadeFuncionarios;
	
	private boolean excluido;

}
