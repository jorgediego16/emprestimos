package com.justa.emprestimos.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaFisicaDTO implements Serializable {

	private static final long serialVersionUID = -5720181433612067437L;

	public PessoaFisicaDTO() {

	}

	private Long id;

	private String nome;

	private String obs;

	private String palavraChave;

	private Long idConsigna;

	private Long idEmpresaTelugo;

	private long idUsuarioCriador;

	private String tratamento;

	private String cpf;

	private String sexo;

	private String rg;

	private String rgExpedidoPor;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate rgExpedidoEm;

	private Integer estadoCivil;

	private String naturalDe;

	private String profissao;

	private String apelido;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate nascimento;

	private String nacionalidade;
	
	private boolean excluido;
	
	private boolean bloqueadoParaEdicao;

}
