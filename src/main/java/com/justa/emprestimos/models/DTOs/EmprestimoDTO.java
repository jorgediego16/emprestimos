package com.justa.emprestimos.models.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmprestimoDTO {

    private char tipoPessoaSolicitante;

    private int quantidadeParcelas;

    private double valorEmprestimo;

    private double valorParcelas;

    private int quantidadeParcelasRestantes;

    private Long idPessoa;

}
