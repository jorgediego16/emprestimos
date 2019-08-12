package com.justa.emprestimos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Endereco implements Serializable {

    private static final long serialVersionUID = -990589837195573169L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cidade;

    private String estado;

    private Integer numero;

    private String logradouro;

    private String complemento;

    @JsonIgnore
    @ManyToOne
    private Pessoa pessoa;
}
