package com.justa.emprestimos.repositories;

import com.justa.emprestimos.models.Pessoa;
import com.justa.emprestimos.models.PessoaFisica;
import com.justa.emprestimos.models.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    /**
     * Get an person
     * @param id
     * @return Pessoa
     */
    @Query("SELECT p FROM Pessoa p WHERE p.id = :id")
    Pessoa obterPessoa (@Param("id") long id);

    /**
     * Get individual by id
     * @param id
     * @return PessoaFisicaDTO
     */
    @Query("SELECT p FROM PessoaFisica p WHERE p.id = :id")
    Optional<PessoaFisica> obterPessoaFisica(@Param("id") long id);

    /**
     * Get legal entity by id
     * @param id
     * @return PessoaJuridicaDTO
     */
    @Query("SELECT p FROM PessoaJuridica p WHERE p.id = :id")
    Optional<PessoaJuridica> obterPessoaJuridica(@Param("id") long id);

    /**
     * Get a person by cpf or cnpj
     * @param cpfOrCnpj
     * @return Long
     */
    @Query("SELECT p.id AS id FROM Pessoa p WHERE p.cpf = :cpfOrCnpj OR p.cnpj = :cpfOrCnpj")
    Long obtemPessoaPorCpfOuCnpj(@Param("cpfOrCnpj") String cpfOrCnpj);

}
