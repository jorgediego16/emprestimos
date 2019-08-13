package com.justa.emprestimos.services;

import com.justa.emprestimos.models.DTOs.EmprestimoDTO;
import com.justa.emprestimos.models.Emprestimo;
import com.justa.emprestimos.models.Pessoa;
import com.justa.emprestimos.repositories.EmprestimoRepository;
import com.justa.emprestimos.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * This method saves a loan
     * @param emprestimoDTO
     * @return Emprestimo
     * @throws Exception
     */
    public Emprestimo saveLoan (EmprestimoDTO emprestimoDTO) throws Exception {

        Emprestimo emprestimo = new Emprestimo();
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(emprestimoDTO.getIdPessoa());

        if (!pessoaOptional.isPresent()) {
            throw new Exception("People not found");
        }

        emprestimo.setQuantidadeParcelas(emprestimoDTO.getQuantidadeParcelas());
        emprestimo.setQuantidadeParcelasRestantes(emprestimoDTO.getQuantidadeParcelasRestantes());
        emprestimo.setTipoPessoaSolicitante('J');
        emprestimo.setValorEmprestimo(emprestimoDTO.getValorEmprestimo());
        emprestimo.setValorParcelas(emprestimoDTO.getValorParcelas());
        emprestimo.setPessoa(pessoaOptional.get());
        emprestimo.setStatusEmprestimo(3);

        return emprestimoRepository.save(emprestimo);
    }

    /**
     * Get loans by id status
     * @param idStatus
     * @return List<Emprestimo>
     */
    public List<Emprestimo> getLoanByStatus (Integer idStatus) {
        return emprestimoRepository.getLoanByStatus(idStatus);
    }

}
