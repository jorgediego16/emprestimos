package com.justa.emprestimos.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("F")
public class PessoaFisica extends Pessoa implements Serializable {

    private static final long serialVersionUID = -5280095970905941584L;

    @Column(length = 12)
    private String tratamento;

    @Column(length = 11, unique = true)
    private String cpf;

    private String sexo;

    @Column(length = 20)
    private String rg;

    @Column(length = 20)
    private String rgExpedidoPor;

    private LocalDate rgExpedidoEm;

    private Integer estadoCivil;

    @Column(length = 72)
    private String naturalDe;

    @Column(length = 30)
    private String profissao;

    @Column(length = 20)
    private String apelido;

    private LocalDate nascimento;

    private String nacionalidade;
}
