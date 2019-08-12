package com.justa.emprestimos.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("J")
public class PessoaJuridica extends Pessoa implements Serializable {

    private static final long serialVersionUID = 4745091136722381402L;

    @Column(length = 150)
    private String razaoSocial;

    @Column(length = 14, unique = true)
    private String cnpj;

    @Column(length = 18)
    private String cgf;

    @Column(length = 2000)
    private String atividade;

    @Column(length = 2000)
    private String atividadeSecundaria;

    private Double faturamento;

    private LocalDateTime fundacao;

    private Integer quantidadeFuncionarios;
}
