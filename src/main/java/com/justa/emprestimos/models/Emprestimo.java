package com.justa.emprestimos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.justa.emprestimos.models.enums.StatusEmprestimo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = -6275873073377615906L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "emprestimo")
    List<Arquivo> arquivosEmprestimo = new ArrayList<>();

    private char tipoPessoaSolicitante;

    @JsonIgnore
    @ManyToOne
    private Pessoa pessoa;

    private int quantidadeParcelas;

    private double valorEmprestimo;

    private double valorParcelas;

    private int quantidadeParcelasRestantes;

    private Integer statusEmprestimo;

    public StatusEmprestimo getStatusEmprestimo () {
        return StatusEmprestimo.get(this.statusEmprestimo);
    }

    public void setStatusEmprestimo (Integer id) {
        this.statusEmprestimo = id;
    }
}
