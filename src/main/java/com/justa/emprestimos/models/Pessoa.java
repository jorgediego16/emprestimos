package com.justa.emprestimos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 8758924356881330336L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nome;

    private LocalDateTime data = LocalDateTime.now();

    @Lob
    private String obs;

    private boolean excluido;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Endereco> enderecos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Emprestimo> emprestimos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Usuario> usuarios = new ArrayList<>();

}
