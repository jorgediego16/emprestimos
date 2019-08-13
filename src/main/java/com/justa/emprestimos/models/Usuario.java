package com.justa.emprestimos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.justa.emprestimos.models.enums.StatusCadastroUsuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1205584669181483812L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @JsonIgnore
    @ManyToOne
    private Pessoa pessoa;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Arquivo> arquivosCadastro = new ArrayList<>();

    private Integer statusCadastro;

    public StatusCadastroUsuario getStatusCadastroUsuario () {
        return StatusCadastroUsuario.get(this.statusCadastro);
    }

    public void setStatusEmprestimo (Integer id) {
        this.statusCadastro = id;
    }


}
