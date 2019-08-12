package com.justa.emprestimos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Arquivo implements Serializable {

	private static final long serialVersionUID = -5769681022078964790L;

	@Id
	private String id;

	private String nomeArquivo;

	private String nomeOriginalArquivo;

	private String extensao;

	private String contentType;

	private LocalDateTime data = LocalDateTime.now();

	private String ipLocal;

	private String ipRemoto;

	private long tamanho;

	@JsonIgnore
	@ManyToOne
	private Emprestimo emprestimo;

}
